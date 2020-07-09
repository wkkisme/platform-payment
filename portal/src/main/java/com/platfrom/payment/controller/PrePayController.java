package com.platfrom.payment.controller;

import com.platfrom.payment.api.PrePayRemoteService;
import com.platfrom.payment.request.PrePayRequest;
import com.platfrom.payment.response.PrePayResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RequestMapping("/prepay")
@RestController
public class PrePayController {

    @Resource
    private PrePayRemoteService prePayRemoteService;

//    @RequestMapping("/doPrepay")
//    @ResponseBody
//    public PrePayResponse doPrepay(@RequestBody PrePayRequest request) {
//        return prePayRemoteService.prePay(request);
//    }

    @RequestMapping("/doPrepay")
    @ResponseBody
    public PrePayResponse doPrepay(String bizId, Long currencyAmt,String payChannel) {
        PrePayRequest request = new PrePayRequest();
        request.setBizId(bizId);
        request.setBizType("DIA");
        request.setCurrencyAmt(currencyAmt);
        request.setCurrencyType("HKD");
        request.setPayChannel(payChannel);
        request.setPayEnv("WEB");
        return prePayRemoteService.prePay(request);
    }
}
