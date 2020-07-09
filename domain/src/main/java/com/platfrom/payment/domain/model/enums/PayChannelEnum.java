package com.platfrom.payment.domain.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PayChannelEnum {

    WECHAT("WECHAT", "微信"),
    ALIPAY("ALIPAY", "支付宝"),
    PAYPAL("PAYPAL", "PayPal"),


    ;


    String channel;

    String name;


    public static PayChannelEnum findByChannel(String channel) {
        for (PayChannelEnum channelEnum : values()) {
            if (channelEnum.channel.equalsIgnoreCase(channel)) {
                return channelEnum;
            }
        }
        return null;
    }
}
