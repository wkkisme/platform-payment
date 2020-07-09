package com.platfrom.payment.api;


import com.platfrom.payment.request.AliPayAckRequest;
import com.platfrom.payment.request.PalPalPayAckRequest;
import com.platfrom.payment.request.WeChatPayAckRequest;
import com.platfrom.payment.response.AliPayPayAckResponse;
import com.platfrom.payment.response.PalPalPayAckResponse;
import com.platfrom.payment.response.WeChatPayAckResponse;

public interface PayAckRemoteService {

    /**
     * 微信支付成功回调
     * @param request
     * @return
     */
    WeChatPayAckResponse weChatAck(WeChatPayAckRequest request);


    /**
     * 支付宝支付成功回调
     * @param request
     * @return
     */
    AliPayPayAckResponse aliPayAck(AliPayAckRequest request);



    /**
     * PayPal支付成功回调
     * @param request
     * @return
     */
    PalPalPayAckResponse payPalAck(PalPalPayAckRequest request);


}
