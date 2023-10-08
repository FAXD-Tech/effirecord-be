package com.faxd.effirecord.repository;

import com.faxd.effirecord.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

   Optional<Card> findByEmployeeIdAndIsDeletedFalse(Long employeeID);
   Optional<Card> findByIdAndIsDeletedFalse(Long cardId);
   Boolean existsByEmployeeIdAndIsDeletedFalse(Long employeeID);
}
