package com.platfrom.payment.domain.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum  PayOrderStateEnum {

    CREATE("CREATE","已登记"),
    SUCCESS("SUCCESS","成功"),
    FAILED("FAILED","失败"),




    ;


    String state;

    String name;


    public static PayOrderStateEnum findByState(String state) {
        for (PayOrderStateEnum stateEnum:values()){
            if (stateEnum.state.equalsIgnoreCase(state)){
                return stateEnum;
            }
        }
        return null;
    }
}
