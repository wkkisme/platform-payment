package com.platfrom.payment.domain.eft;

import com.platfrom.payment.domain.eft.dto.request.EftCreateOrderRequest;
import com.platfrom.payment.domain.eft.dto.response.EftCreateOrderResponse;
import org.springframework.stereotype.Component;


@Component
public class EftRemoteService {


    public EftCreateOrderResponse createAcquireOrder(EftCreateOrderRequest request) {
        return EftHttpUtils.doGet(EftUrl.CREATE_ORDER, request,
                (httpResponse) -> {
                    EftCreateOrderResponse response = new EftCreateOrderResponse();
                    response.setRedirectUrl(httpResponse.getUrl());
                    return response;
                });
    }
}
