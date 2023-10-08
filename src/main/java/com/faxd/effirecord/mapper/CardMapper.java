package com.faxd.effirecord.mapper;

import com.faxd.effirecord.config.CardConfig;
import com.faxd.effirecord.dto.card.CardInfoDto;
import com.faxd.effirecord.dto.card.CardPostDto;
import com.faxd.effirecord.model.Card;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CardMapper {
    Card cardPostDtoToCard(CardPostDto cardPostDto);

    @Mapping(source = "employee.name", target = "employeeName")
    CardInfoDto cardToCardInfoDto(Card card);
}
