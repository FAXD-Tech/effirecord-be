package com.faxd.effirecord.constant;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.faxd.effirecord.exception.PaymentMethodException;

public enum PaymentMethodType {
    WECHAT("微信支付"),
    ALIPAY("支付宝"),
    BANK_TRANSFER("银行转账"),
    CASH("现金");

    private final String value;
    PaymentMethodType(String value) {
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }

}
