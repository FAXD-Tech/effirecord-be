package com.faxd.effirecord.repository;

import com.faxd.effirecord.dto.payroll.PayrollInfoDto;
import com.faxd.effirecord.model.Payroll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PayrollRepository extends JpaRepository<Payroll, Long> {

    Optional<List<Payroll>> findALLByPayeeIdAndIsDeletedFalseAndPaidDateBetween(Long payeeId, LocalDate start, LocalDate end);

    Optional<Payroll> findByIdAndIsDeletedFalse(Long id);
    @Modifying
    @Query("UPDATE Payroll p SET p.isDeleted = true WHERE p.id = :payrollId AND p.isDeleted = false")
    void deletePayrollById(@Param("payrollId") Long payrollId);
}
