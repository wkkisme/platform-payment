package com.platfrom.payment.domain.model.order;

import com.platfrom.payment.domain.model.enums.PayCurrencyTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayCurrency {

    private Long amount;

    private PayCurrencyTypeEnum currencyTypeEnum;

}
