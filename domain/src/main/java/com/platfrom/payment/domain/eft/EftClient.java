package com.platfrom.payment.domain.eft;

import com.platfrom.payment.domain.config.AppConfig;
import com.platfrom.payment.domain.eft.dto.request.EftCreateOrderRequest;
import com.platfrom.payment.domain.eft.dto.response.EftCreateOrderResponse;
import com.platfrom.payment.domain.model.acquire.AcquireOrder;
import com.platfrom.payment.domain.model.order.PayOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class EftClient {


    @Autowired
    private EftRemoteService eftRemoteService;

    public AcquireOrder createAcquireOrder(PayOrder payOrder) {

        EftCreateOrderRequest request = new EftCreateOrderRequest();

        request.setService("SALE");
        request.setPayment_type(payOrder.getChannel().getChannel());
        request.setMid(AppConfig.get(EFTConfigConstant.MID));
        request.setReturn_url(buildReturnUrl(payOrder));
        request.setMerch_ref_no("mcftest" + payOrder.getPayNo());
        request.setGoods_subject("123");
        request.setGoods_body("123");

        request.setTrans_amount(payOrder.getPayCurrency().getAmount().toString());
        request.setWallet("HK");
        request.setApp_pay(payOrder.getEnv().getEnv());
        request.setActive_time(buildActiveTime(payOrder));
        request.setApi_version(AppConfig.get(EFTConfigConstant.APP_VERSION));
        request.setReuse("Y");

        EftCreateOrderResponse response = eftRemoteService.createAcquireOrder(request);

        AcquireOrder acquireOrder = new AcquireOrder();

        acquireOrder.setChannel(payOrder.getChannel());
        acquireOrder.setPayNo(payOrder.getPayNo());
        acquireOrder.setRedirectUrl(response.getRedirectUrl());
        acquireOrder.setChannel(payOrder.getChannel());
        return acquireOrder;
    }

    private String buildActiveTime(PayOrder payOrder) {
        return AppConfig.get(EFTConfigConstant.ACTIVE_TIME);
    }

    private String buildReturnUrl(PayOrder payOrder) {
        return "http://acroxx.cn";
    }
}
