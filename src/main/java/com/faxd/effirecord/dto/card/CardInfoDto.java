package com.faxd.effirecord.dto.card;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardInfoDto {

    private String employeeName;

    private String cardNumber;

    private String cardHolder;

    private String bankName;

    private String province;

    private String city;

    private String bankOutlet;
}
