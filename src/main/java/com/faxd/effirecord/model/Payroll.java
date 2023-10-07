package com.faxd.effirecord.model;

import com.faxd.effirecord.constant.PaymentMethodType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "payroll")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Payroll {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @CreationTimestamp
    private LocalDateTime createdTime;

    @Column(name = "is_deleted")
    @Builder.Default
    private Boolean isDeleted = false;

}
