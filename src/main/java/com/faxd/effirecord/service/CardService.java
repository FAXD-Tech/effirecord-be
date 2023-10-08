package com.faxd.effirecord.service;

import com.faxd.effirecord.config.CardConfig;
import com.faxd.effirecord.dto.card.CardInfoDto;
import com.faxd.effirecord.dto.card.CardPostDto;
import com.faxd.effirecord.dto.card.CardPutDto;
import com.faxd.effirecord.exception.BankNotFoundException;
import com.faxd.effirecord.exception.CardHasExistedException;
import com.faxd.effirecord.exception.CardNotFoundException;
import com.faxd.effirecord.exception.EmployeeNotFoundException;
import com.faxd.effirecord.exception.EmployeeWithoutCardException;
import com.faxd.effirecord.mapper.CardMapper;
import com.faxd.effirecord.model.Card;
import com.faxd.effirecord.repository.CardRepository;
import com.faxd.effirecord.repository.EmployeeRepository;
import com.faxd.effirecord.utils.BeanHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CardService {

    private final Logger logger = LoggerFactory.getLogger(CardService.class);
    private final EmployeeRepository employeeRepository;
    private final CardMapper cardMapper;
    private final CardRepository cardRepository;
    private final CardConfig cardConfig;

    public void createCard(final CardPostDto cardPostDto) {
        Long employeeId = cardPostDto.getEmployeeId();
        employeeRepository.findByIdAndIsVerifiedTrueAndIsDeletedFalse(cardPostDto.getEmployeeId()).ifPresentOrElse(
                employee -> {
                    Boolean existed = cardRepository.existsByEmployeeIdAndIsDeletedFalse(employeeId);
                    if (!existed){
                        String bankName = cardConfig.getKeyByValue(cardPostDto.getBankName());
                        Card card = cardMapper.cardPostDtoToCard(cardPostDto);
                        card.setBankName(bankName);
                        card.setEmployee(employee);
                        cardRepository.save(card);
                    }else {
                        logger.info("Task: create a card for employee; message: card has existed for employee with id: {}", employeeId);
                        throw new CardHasExistedException(employeeId);
                    }
                },
                () -> {
                    logger.warn("Task: post card info for employee, The employee with id: {} does not exist!", employeeId);
                    throw new EmployeeNotFoundException(employeeId);
                }
        );
    }

    public CardInfoDto getCard(Long employeeId) {
        return cardRepository.findByEmployeeIdAndIsDeletedFalse(employeeId)
                .map(cardMapper::cardToCardInfoDto)
                .map(this::updateBankName)
                .orElseThrow(() -> new EmployeeWithoutCardException(employeeId));
    }

    private CardInfoDto updateBankName(CardInfoDto cardInfoDto) {
        String bankName = cardInfoDto.getBankName();
        cardInfoDto.setBankName(getBankValueName(bankName));
        return cardInfoDto;
    }

    private String getBankValueName(String bankKeyName){
        return Optional.ofNullable(cardConfig.getBanks().get(bankKeyName))
                .orElseThrow(() -> new BankNotFoundException(bankKeyName));
    }

    public void updateCard(final Long cardId, final CardPutDto cardPutDto) {
        cardRepository.findByIdAndIsDeletedFalse(cardId).ifPresentOrElse(
                card -> BeanHelper.CopyDtoIntoEntity(card,cardPutDto),
                () ->{
                  logger.warn("Task: update card info for employee; message: the card with id {} does not exist!", cardId);
                  throw new CardNotFoundException(cardId);
                }
        );
    }

    public void deleteCard(Long cardId) {
        cardRepository.findByIdAndIsDeletedFalse(cardId).ifPresent(card -> card.setIsDeleted(true));
    }
}
