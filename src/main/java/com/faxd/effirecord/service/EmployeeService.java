package com.faxd.effirecord.service;

import com.faxd.effirecord.dto.employee.EmployeeInfoDto;
import com.faxd.effirecord.dto.employee.EmployeePostDto;
import com.faxd.effirecord.dto.employee.EmployeePutDto;
import com.faxd.effirecord.exception.EmployeeNotFoundException;
import com.faxd.effirecord.mapper.EmployeeMapper;
import com.faxd.effirecord.model.Employee;
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
public class EmployeeService {
    private final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    public EmployeeInfoDto signIn(final EmployeePostDto employeePostDto){
        Employee employees = employeeMapper.employeePostDtoToEmployee(employeePostDto);
        Employee savedEmployees = employeeRepository.save(employees);
        return employeeMapper.employeeToEmployInfoDto(savedEmployees);
    }

    public EmployeeInfoDto getEmployeeInfo(final Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        Employee employeeValue = employee.orElseThrow(() -> new EmployeeNotFoundException(id));
        return employeeMapper.employeeToEmployInfoDto(employeeValue);
    }

    public void upsertEmployeeInfo(final Long id, final EmployeePutDto employeePutDto) {
        Optional<Employee> employeesOptional = employeeRepository.findById(id);
        employeesOptional.ifPresentOrElse(
                employee -> BeanHelper.CopyDtoIntoEntity(employee, employeePutDto),
               () -> {
                    logger.warn("Task: {}, employee_id: {}, updated_employee_dto: {}.",
                            "update not exist employee information",
                            id, employeePutDto.toString());
                   Employee employees = employeeMapper.employeePutDtoToEmployee(employeePutDto);
                   employeeRepository.save(employees);
               }
        );

    }

    public void verifyEmployee(Long id) {
        Optional<Employee> employeesOptional = employeeRepository.findById(id);
        employeesOptional.ifPresentOrElse(employees -> employees.setVerified(true),
                () -> {
                    logger.error("the employee with id: {} does not exist", id);
                    throw new EmployeeNotFoundException(id);
                });
    }

    public void deleteEmployeeById(Long id) {
        Optional<Employee> employeesOptional = employeeRepository.findById(id);
        employeesOptional.ifPresentOrElse(employees -> employees.setIsDeleted(true),
                () -> {
                    logger.error("the employee with id: {} does not exist", id);
                    throw new EmployeeNotFoundException(id);
                });
    }
}

