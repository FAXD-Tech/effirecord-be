package com.faxd.effirecord.dto.payroll;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.faxd.effirecord.constant.PaymentMethodType;
import com.faxd.effirecord.utils.PaymentMethodTypeSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class PayrollInfoDto {
    private Long id;

    private String payeeName;

    private BigDecimal paidMoney;

    private LocalDate paidDate;

    @JsonSerialize(using = PaymentMethodTypeSerializer.class)
    private PaymentMethodType paymentMethod;

    private String payerName;

    private String comment;
}
