package com.faxd.effirecord.model;

import com.faxd.effirecord.constant.PaymentMethodType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;


@Entity
@Table(name = "payroll")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Payroll extends BaseEntity{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payee_id")
    private Employee payee;

    @Column(name = "paid_money",nullable = false)
    private BigDecimal paidMoney;

    @Column(name = "paid_date",nullable = false)
    private LocalDate paidDate;

    @Column
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payer_id")
    private Employee payer;

    @Column(name = "payment_method",nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentMethodType paymentMethod;
}
