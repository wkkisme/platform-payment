package com.platfrom.payment.domain.model.config;

import com.platfrom.payment.domain.model.enums.PayChannelEnum;
import com.platfrom.payment.domain.model.enums.PayChannelStateEum;
import lombok.Data;


@Data
public class PayChannelConfig {

    private PayChannelEnum channelEnum;

    private PayChannelStateEum stateEum;
}
