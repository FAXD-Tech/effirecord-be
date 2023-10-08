package com.faxd.effirecord.service;

import com.faxd.effirecord.dto.payroll.PayrollInfoDto;
import com.faxd.effirecord.dto.payroll.PayrollPostDto;
import com.faxd.effirecord.exception.EmployeeNotFoundException;
import com.faxd.effirecord.exception.PayrollNotFoundException;
import com.faxd.effirecord.mapper.PayrollMapper;
import com.faxd.effirecord.model.Payroll;
import com.faxd.effirecord.repository.EmployeeRepository;
import com.faxd.effirecord.repository.PayrollRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class PayrollService {

    private final Logger logger = LoggerFactory.getLogger(PayrollService.class);

    private final PayrollRepository payrollRepository;
    private final EmployeeRepository employeeRepository;
    private final PayrollMapper payrollMapper;

    public void createPayroll(List<PayrollPostDto> payrollPostDtoList) {
        payrollPostDtoList.forEach(payrollPostDto ->
                employeeRepository.findByIdAndIsDeletedFalseAndIsVerifiedTrue(payrollPostDto.getPayeeId())
                        .ifPresentOrElse(
                                employee -> {
                                    Payroll payroll = payrollMapper.payrollPostDtoToPayroll(payrollPostDto);
                                    payroll.setPayee(employee);
                                    employeeRepository.findByIdAndIsDeletedFalseAndIsVerifiedTrue(payrollPostDto.getPayerId())
                                            .ifPresentOrElse(payroll::setPayer,
                                                    () -> {
                                                        logger.warn("Can not find the payer according to the employee id: {}",
                                                                payrollPostDto.getPayerId());
                                                        throw new EmployeeNotFoundException(payrollPostDto.getPayerId());
                                                    });
                                    payrollRepository.save(payroll);
                                },
                                () -> {
                                    logger.warn("Can not find the payee according to the employee id: {}",
                                            payrollPostDto.getPayeeId());
                                    throw new EmployeeNotFoundException(payrollPostDto.getPayeeId());
                                }
                        ));
    }

    public List<PayrollInfoDto> getPayrollRecordByPayeeId(final Long payeeId, final LocalDate start, final LocalDate end) {
        return payrollRepository.findALLByPayeeIdAndIsDeletedFalseAndPaidDateBetween(payeeId, start, end)
                .map(payrolls -> payrolls.stream().map(payrollMapper::payrollToPayrollInfoDto).toList())
                .orElse(Collections.emptyList());
    }


    public void updatePayroll(Long payrollId, PayrollPostDto payrollPostDto) {
        payrollRepository.findByIdAndIsDeletedFalse(payrollId).ifPresentOrElse(
                payroll ->{
                    deletePayroll(payrollId);
                    createPayroll(List.of(payrollPostDto));
                },
                () -> {
                    logger.warn("This payroll record may have been updated before");
                    throw new PayrollNotFoundException(payrollId);
                }
        );

    }

    public void deletePayroll(Long payrollId) {
        payrollRepository.deletePayrollById(payrollId);
    }
}
