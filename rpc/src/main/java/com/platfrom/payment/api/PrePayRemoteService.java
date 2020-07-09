package com.platfrom.payment.api;


import com.platfrom.payment.request.PrePayRequest;
import com.platfrom.payment.response.PrePayResponse;

public interface PrePayRemoteService {

    /**
     * 预支付
     * @param request
     * @return
     */
    PrePayResponse prePay(PrePayRequest request);


}
