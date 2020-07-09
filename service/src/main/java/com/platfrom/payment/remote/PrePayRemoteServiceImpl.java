package com.platfrom.payment.remote;


import com.platfrom.payment.api.PrePayRemoteService;
import com.platfrom.payment.common.enums.PaymentErrorEnum;
import com.platfrom.payment.common.exception.PaymentException;
import com.platfrom.payment.common.model.Identity;
import com.platfrom.payment.domain.core.PrePayCoreService;
import com.platfrom.payment.domain.model.acquire.AcquireOrder;
import com.platfrom.payment.domain.model.enums.PayChannelEnum;
import com.platfrom.payment.domain.model.enums.PayCurrencyTypeEnum;
import com.platfrom.payment.domain.model.enums.PayEnvEnum;
import com.platfrom.payment.domain.model.order.PayCurrency;
import com.platfrom.payment.domain.model.order.PayOrder;
import com.platfrom.payment.domain.repository.PayOrderRepository;
import com.platfrom.payment.request.PrePayRequest;
import com.platfrom.payment.response.PrePayResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service("prePayRemoteService")
@Slf4j
public class PrePayRemoteServiceImpl implements PrePayRemoteService {

    @Autowired
    private PrePayCoreService prePayCoreService;

    @Autowired
    private PayOrderRepository payOrderRepository;

    @Override
    public PrePayResponse prePay(PrePayRequest request) {
        PrePayResponse response = new PrePayResponse();


        if (StringUtils.isBlank(request.getBizId()) || StringUtils.isBlank(request.getBizType())) {
            log.info("业务号参数异常");
            throw new PaymentException(PaymentErrorEnum.PARAM_ERROR);
        }

        PayCurrencyTypeEnum currencyTypeEnum = PayCurrencyTypeEnum.findByType(request.getCurrencyType());
        if (Objects.isNull(currencyTypeEnum) || request.getCurrencyAmt() == null) {
            log.info("收款币种，收款金额参数异常");
            throw new PaymentException(PaymentErrorEnum.PARAM_ERROR);
        }

        Identity biz = new Identity(request.getBizId(), request.getBizType());

        PayOrder payOrder = payOrderRepository.loadByBiz(biz);

        AcquireOrder acquireOrder = null;
        if (payOrder != null) {
            acquireOrder = prePayCoreService.findAcquireOrder(payOrder);
        } else {

            PayChannelEnum payChannel = PayChannelEnum.findByChannel(request.getPayChannel());

            if (Objects.isNull(payChannel)) {
                throw new PaymentException(PaymentErrorEnum.PAY_CHANNEL_ERROR);
            }

            PayEnvEnum payEnv = PayEnvEnum.findByEnv(request.getPayEnv());

            if (PayEnvEnum.PC != payEnv) {
                log.info("仅支持PC渠道支付");
                throw new PaymentException(PaymentErrorEnum.PARAM_ERROR);
            }


            PayCurrency payCurrency = new PayCurrency();
            payCurrency.setAmount(request.getCurrencyAmt());
            payCurrency.setCurrencyTypeEnum(PayCurrencyTypeEnum.findByType(request.getCurrencyType()));

            acquireOrder = prePayCoreService.doPrePay(biz, payCurrency, payChannel, payEnv, request.getDigest());
            payOrder = payOrderRepository.loadByBiz(biz);

        }

        assemblePrePayResponse(response, acquireOrder, payOrder);

        return response;
    }

    private void assemblePrePayResponse(PrePayResponse response, AcquireOrder acquireOrder, PayOrder payOrder) {
        response.setPayNo(payOrder.getPayNo());
        response.setRedirectUrl(acquireOrder.getRedirectUrl());
    }


}
