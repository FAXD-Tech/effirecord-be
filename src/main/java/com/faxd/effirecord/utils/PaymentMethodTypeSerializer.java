package com.faxd.effirecord.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.faxd.effirecord.constant.PaymentMethodType;

import java.io.IOException;

public class PaymentMethodTypeSerializer extends JsonSerializer<PaymentMethodType> {
    @Override
    public void serialize(PaymentMethodType paymentMethodType, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(paymentMethodType.getValue());
    }
}