package com.platfrom.payment.common.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum  PaymentErrorEnum {


    PARAM_ERROR(100001,"业务参数缺失"),
    NETWORK_ERROR(100002,"网络错误"),


    //------------预支付
    PAY_CHANNEL_ERROR(200001,"请选择支付渠道"),


    //------------外部异常
    EFT_REQUEST_ERROR(300001,"EFT请求对象参数错误"),
    SHA_256_ERROR(300002,"SHA-256failed"),
    SIGNATURE_VERIFIED_FAILED(300003,"摘要校验失败"),
    EFT_CONNECT_FAILED(300004,"连接失败或超时"),



    ;


    int errorCode;

    String errorMessage;


}
