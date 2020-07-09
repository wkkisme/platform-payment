package com.platfrom.payment.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class PrePayRequest implements Serializable {

    private String bizId;


    private String bizType;


    private Long currencyAmt;


    private String currencyType;


    private String payEnv;


    private String payChannel;


    private String digest;


}
