package com.platfrom.payment.domain.eft.dto.response;

import com.platfrom.payment.domain.eft.dto.EftResponse;
import lombok.Data;

@Data
public class EftCreateOrderResponse extends EftResponse {

    private String orderId;

    private String redirectUrl;

}
