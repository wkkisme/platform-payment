package com.platfrom.payment.api;


import com.platfrom.payment.request.FindPayOrderRequest;
import com.platfrom.payment.response.FindPayOrderResponse;

public interface PayQueryRemoteService {

    /**
     * 支付单查询
     * @param request
     * @return
     */
    FindPayOrderResponse findPayOrder(FindPayOrderRequest request);



}
