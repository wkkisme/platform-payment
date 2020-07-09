package com.platfrom.payment.domain.acquirers;

import com.platfrom.payment.domain.acquirers.eft.EftAcquireService;
import com.platfrom.payment.domain.model.order.PayOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AcquireFactory {


    @Autowired
    private EftAcquireService eftAcquireService;


    /**
     * designed for routing to target acquireService
     * but only eftAcquireService here
     *
     * @param payOrder
     * @return
     */
    public AcquireService getAcquireService(PayOrder payOrder) {
        return eftAcquireService;
    }
}
