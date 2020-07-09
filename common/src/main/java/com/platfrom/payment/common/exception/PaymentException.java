package com.platfrom.payment.common.exception;


import com.platfrom.payment.common.enums.PaymentErrorEnum;

public class PaymentException extends RuntimeException {

    private int code;

    public PaymentException() {
        super();
    }

    public PaymentException(int code, String message) {
        super(message);
        this.code = code;
    }

    public PaymentException(PaymentErrorEnum errorEnum) {
        super(errorEnum.getErrorMessage());
        this.code = errorEnum.getErrorCode();
    }


    public PaymentException(PaymentErrorEnum errorEnum, String message) {
        super(message);
        this.code = errorEnum.getErrorCode();
    }
}
