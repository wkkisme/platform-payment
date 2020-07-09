package com.platfrom.payment.domain.eft.dto.request;

import com.platfrom.payment.domain.eft.dto.EftRequest;
import com.platfrom.payment.domain.eft.dto.EftSignature;
import lombok.Data;

@Data
public class EftCreateOrderRequest extends EftRequest {

    @EftSignature
    private String service;

    @EftSignature
    private String payment_type;

    @EftSignature
    private String mid;

    private String return_url;

    @EftSignature
    private String merch_ref_no;

    private String goods_subject;

    private String goods_body;

    @EftSignature
    private String trans_amount;

    private String notify_url;

    private String wallet;

    private String app_pay;

    private String tid;

    private String active_time;

    private String api_version;

    private String lang;

    private String reuse;
}
