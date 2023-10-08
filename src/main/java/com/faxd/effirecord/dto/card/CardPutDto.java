package com.faxd.effirecord.dto.card;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardPutDto {

    @Pattern(regexp = "^[0-9]{12,19}$", message = "Card number must be between 12 and 19 digits")
    private String cardNumber;

    private String cardHolder;

    private String bankName;

    private String province;

    private String city;

    private String bankOutlet;
}
