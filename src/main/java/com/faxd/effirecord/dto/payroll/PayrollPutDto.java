package com.faxd.effirecord.dto.payroll;

import com.faxd.effirecord.constant.PaymentMethodType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PayrollPutDto {

    @NotNull
    private Long payeeId;

    @Positive
    private BigDecimal paidMoney;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate paidDate;

    @NotNull
    private Long payerId;

    @PastOrPresent
    @Enumerated(EnumType.STRING)
    private PaymentMethodType paymentMethod;

    private String comment;
}
