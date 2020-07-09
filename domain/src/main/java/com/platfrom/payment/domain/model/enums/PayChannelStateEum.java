package com.platfrom.payment.domain.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PayChannelStateEum {


    DISABLE("DISABLE", "未启用"),


    ENABLE("ENABLE", "启用"),


    ;


    String state;

    String name;


    public static PayChannelStateEum findByState(String state) {
        for (PayChannelStateEum stateEum : values()) {
            if (stateEum.state.equalsIgnoreCase(state)) {
                return stateEum;
            }
        }
        return null;
    }
}
