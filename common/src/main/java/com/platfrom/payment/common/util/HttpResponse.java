package com.platfrom.payment.common.util;

import lombok.Data;

import java.util.Map;

@Data
public class HttpResponse {

    private String url;

    private String response;

    private Map<String, String> param;
}
