package com.platfrom.payment.domain.acquirers;


import com.platfrom.payment.domain.model.acquire.AcquireOrder;
import com.platfrom.payment.domain.model.order.PayOrder;

public interface AcquireService {


    AcquireOrder createAcquireOrder(PayOrder payOrder);



    AcquireOrder fetchUpdatedAcquireOrder(AcquireOrder acquireOrder);



}
