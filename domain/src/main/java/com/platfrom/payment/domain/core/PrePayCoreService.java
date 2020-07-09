package com.platfrom.payment.domain.core;


import com.platfrom.payment.common.model.Identity;
import com.platfrom.payment.domain.acquirers.AcquireFactory;
import com.platfrom.payment.domain.acquirers.AcquireService;
import com.platfrom.payment.domain.model.acquire.AcquireOrder;
import com.platfrom.payment.domain.model.enums.PayChannelEnum;
import com.platfrom.payment.domain.model.enums.PayEnvEnum;
import com.platfrom.payment.domain.model.enums.PayOrderStateEnum;
import com.platfrom.payment.domain.model.order.PayCurrency;
import com.platfrom.payment.domain.model.order.PayOrder;
import com.platfrom.payment.domain.repository.AcquireOrderRepository;
import com.platfrom.payment.domain.repository.PayOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service("prePayCoreService")
public class PrePayCoreService {


    @Autowired
    private AcquireFactory acquireFactory;

    @Autowired
    private PayOrderRepository payOrderRepository;

    @Autowired
    private AcquireOrderRepository acquireOrderRepository;


    @Autowired
    private SequenceService sequenceService;


//    @Transactional(value="defaultTransactionManager",propagation= Propagation.REQUIRED,isolation= Isolation.READ_UNCOMMITTED,rollbackFor=Throwable.class)
    public AcquireOrder doPrePay(Identity biz, PayCurrency payCurrency
            , PayChannelEnum payChannel, PayEnvEnum payEnv, String digest) {

        PayOrder payOrder = createPayOrder(biz, payCurrency, payChannel, payEnv, digest);

        payOrderRepository.store(payOrder);

        AcquireService acquireService = acquireFactory.getAcquireService(payOrder);

        AcquireOrder acquireOrder = acquireService.createAcquireOrder(payOrder);

        acquireOrderRepository.store(acquireOrder);

        return acquireOrder;

    }


    public AcquireOrder findAcquireOrder(PayOrder payOrder) {

        return acquireOrderRepository.loadByPayNo(payOrder.getPayNo());
    }


    private PayOrder createPayOrder(Identity biz, PayCurrency payCurrency, PayChannelEnum payChannel
            , PayEnvEnum payEnv, String digest) {
        PayOrder payOrder = new PayOrder();
        payOrder.setBiz(biz);
        payOrder.setPayCurrency(payCurrency);
        payOrder.setChannel(payChannel);
        payOrder.setEnv(payEnv);
        payOrder.setPayNo(sequenceService.getNextStr());
        payOrder.setPayCreateTime(new Date());
        payOrder.setState(PayOrderStateEnum.CREATE);
        payOrder.setPayDigest(digest);
        return payOrder;
    }

}
