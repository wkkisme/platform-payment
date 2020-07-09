package com.platfrom.payment.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class PrePayResponse implements Serializable {

    private String redirectUrl;

    private String payNo;
}
