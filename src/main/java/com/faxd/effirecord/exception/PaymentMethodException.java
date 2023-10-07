package com.faxd.effirecord.exception;

public class PaymentMethodException extends RuntimeException {
    public PaymentMethodException(String type) {
        super("Only support alipay, wechat, bank transfer and cash!You can not used " + type);
    }
}
