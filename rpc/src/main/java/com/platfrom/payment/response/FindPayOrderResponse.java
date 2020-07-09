package com.platfrom.payment.response;

import com.platfrom.payment.dto.PayOrderDTO;
import lombok.Data;

@Data
public class FindPayOrderResponse {

    private PayOrderDTO payOrderDTO;
}
