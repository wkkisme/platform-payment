package com.platfrom.payment.domain.model.order;

import com.platfrom.payment.common.model.Identity;
import com.platfrom.payment.domain.model.enums.PayChannelEnum;
import com.platfrom.payment.domain.model.enums.PayEnvEnum;
import com.platfrom.payment.domain.model.enums.PayOrderStateEnum;
import lombok.Data;

import java.util.Date;
import java.util.Map;

@Data
public class PayOrder {

    private Long id;

    private Identity biz;

    private PayCurrency payCurrency;

    private PayChannelEnum channel;

    private PayEnvEnum env;

    private String payNo;

    private PayOrderStateEnum state;

    private Date payCreateTime;

    private Date payFinishTime;

    private String payDigest;

    private Map<String, String> bizExt;
}
