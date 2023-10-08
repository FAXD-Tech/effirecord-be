package com.faxd.effirecord.controller;

import com.faxd.effirecord.dto.card.CardInfoDto;
import com.faxd.effirecord.dto.card.CardPostDto;
import com.faxd.effirecord.dto.card.CardPutDto;
import com.faxd.effirecord.service.CardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/card")
@RequiredArgsConstructor
@Validated
@Tag(name = "card", description = "Card APIs")
public class CardController {

    private final CardService cardService;

    @Operation(
            summary = "Create a card",
            description = "Create a card object. Note: one employee just has one card")
    @PostMapping("")
    public void createCard(@Valid @RequestBody CardPostDto cardPostDto){
        cardService.createCard(cardPostDto);
    }


    @Operation(
            summary = "Retrieve a card",
            description = "Retrieve a card info by employee id. The response is the card infomation")
    @GetMapping("/employee/{employeeId}")
    public CardInfoDto getCard(@PathVariable Long employeeId){
        return cardService.getCard(employeeId);
    }

    @Operation(
            summary = "Update a card",
            description = "Update a card info by putting some information.")
    @PutMapping("/update/{cardId}")
    public void updateCard(@PathVariable Long cardId, @Valid @RequestBody CardPutDto cardPutDto){
        cardService.updateCard(cardId, cardPutDto);
    }


    @Operation(
            summary = "Delete a card",
            description = "Delete a card info by card id.")
    @PutMapping("/delete/{cardId}")
    public void deleteCard(@PathVariable Long cardId){
        cardService.deleteCard(cardId);
    }

}
