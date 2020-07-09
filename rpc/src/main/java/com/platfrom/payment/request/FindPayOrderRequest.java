package com.platfrom.payment.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class FindPayOrderRequest implements Serializable {


    private String payNo;

    private String bizId;

    private String bizType;


}
