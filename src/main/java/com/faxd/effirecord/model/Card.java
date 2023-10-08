package com.faxd.effirecord.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cards")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Card extends BaseEntity{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "card_holder")
    private String cardHolder;

    @Column(name = "bank_name")
    private String bankName;

    private String province;

    private String city;

    @Column(name = "bank_outlet")
    private String bankOutlet;

    private String comments;
}
