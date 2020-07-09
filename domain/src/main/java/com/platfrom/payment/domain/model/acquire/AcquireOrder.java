package com.platfrom.payment.domain.model.acquire;

import com.platfrom.payment.domain.model.enums.PayChannelEnum;
import lombok.Data;

@Data
public class AcquireOrder {

    private Long id;

    private PayChannelEnum channel;

    private String payNo;

    private String acquireNo;

    private String tradeNo;

    private String redirectUrl;


}
