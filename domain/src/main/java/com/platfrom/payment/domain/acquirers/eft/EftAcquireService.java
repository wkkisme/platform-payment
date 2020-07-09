package com.platfrom.payment.domain.acquirers.eft;

import com.platfrom.payment.domain.acquirers.AcquireService;
import com.platfrom.payment.domain.eft.EftClient;
import com.platfrom.payment.domain.model.acquire.AcquireOrder;
import com.platfrom.payment.domain.model.order.PayOrder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class EftAcquireService implements AcquireService {

    @Resource
    private EftClient eftClient;

    @Override
    public AcquireOrder createAcquireOrder(PayOrder payOrder) {


        return eftClient.createAcquireOrder(payOrder);
    }

    @Override
    public AcquireOrder fetchUpdatedAcquireOrder(AcquireOrder acquireOrder) {
        return null;
    }
}
