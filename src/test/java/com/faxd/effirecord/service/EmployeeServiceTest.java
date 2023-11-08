package com.faxd.effirecord.service;

import com.faxd.effirecord.dto.employee.EmployeeInfoDto;
import com.faxd.effirecord.dto.employee.EmployeePostDto;
import com.faxd.effirecord.dto.employee.EmployeePutDto;
import com.faxd.effirecord.exception.EmployeeNotFoundException;
import com.faxd.effirecord.mapper.EmployeeMapper;
import com.faxd.effirecord.model.Employee;
import com.faxd.effirecord.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private EmployeeMapper employeeMapper;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    public void testSignIn() {
        EmployeePostDto postDtoSample = createEmployeePostDtoSample();
        Employee employeeSample = createEmployeeSample();
        EmployeeInfoDto infoDtoSample = createEmployeeInfoDtoSample();

        when(employeeMapper.employeePostDtoToEmployee(postDtoSample)).thenReturn(employeeSample);
        when(employeeRepository.save(employeeSample)).thenReturn(employeeSample);
        when(employeeMapper.employeeToEmployInfoDto(employeeSample)).thenReturn(infoDtoSample);

        EmployeeInfoDto result = employeeService.signIn(postDtoSample);

        assertEquals(infoDtoSample, result);

        verify(employeeMapper, times(1)).employeePostDtoToEmployee(postDtoSample);
        verify(employeeRepository, times(1)).save(employeeSample);
        verify(employeeMapper, times(1)).employeeToEmployInfoDto(employeeSample);
    }

    @Test
    public void testGetEmployeeInfo() {
        Long id = 1L;
        Employee employeeSample = createEmployeeSample();

        when(employeeRepository.findById(id)).thenReturn(Optional.of(employeeSample));
        when(employeeMapper.employeeToEmployInfoDto(employeeSample)).thenReturn(createEmployeeInfoDtoSample());

        EmployeeInfoDto result = employeeService.getEmployeeInfo(id);

        assertNotNull(result);
        verify(employeeRepository, times(1)).findById(id);
    }

    @Test
    public void testGetEmployeeInfoNotFound() {
        Long id = 1L;
        when(employeeRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EmployeeNotFoundException.class, () -> {
            employeeService.getEmployeeInfo(id);
        });
    }

    @Test
    public void testUpsertEmployeeInfoExisting() {
        Long id = 1L;
        EmployeePutDto putDto = new EmployeePutDto();
        Employee existingEmployee = createEmployeeSample();

        when(employeeRepository.findById(id)).thenReturn(Optional.of(existingEmployee));
        when(employeeMapper.employeePutDtoToEmployee(putDto)).thenReturn(existingEmployee);

        employeeService.upsertEmployeeInfo(id, putDto);

        verify(employeeRepository, times(1)).findById(id);
        verify(employeeRepository, times(1)).save(existingEmployee);
    }

    @Test
    public void testUpsertEmployeeInfoNotExisting() {

        Long id = 1L;
        EmployeePutDto putDto = createEmployeePutDtoSample();

        when(employeeRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EmployeeNotFoundException.class, () -> {
            employeeService.upsertEmployeeInfo(id, putDto);
        });

        verify(employeeRepository, times(1)).findById(id);
    }

    @Test
    public void testVerifyEmployee() {
        Long id = 1L; // Sample ID
        Employee existingEmployee = createEmployeeSample();

        when(employeeRepository.findById(id)).thenReturn(Optional.of(createEmployeeSample()));

        employeeService.verifyEmployee(id);

        assertTrue(createEmployeeSample().isVerified());
        verify(employeeRepository, times(1)).findById(id);
    }

    @Test
    public void testVerifyEmployeeNotFound() {
        Long id = 1L; // Sample ID
        when(employeeRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EmployeeNotFoundException.class, () -> {
            employeeService.verifyEmployee(id);
        });
    }

    @Test
    public void testDeleteEmployeeById() {
        Long id = 1L;
        Employee employeeSample = createEmployeeSample();

        when(employeeRepository.findById(id)).thenReturn(Optional.of(employeeSample));

        employeeService.deleteEmployeeById(id);

        assertTrue(employeeSample.getIsDeleted());
        verify(employeeRepository, times(1)).findById(id);
    }

    @Test
    public void testDeleteEmployeeByIdNotFound() {
        Long id = 1L; // Sample ID
        when(employeeRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EmployeeNotFoundException.class, () -> {
            employeeService.deleteEmployeeById(id);
        });
    }

    private Employee createEmployeeSample() {
        return Employee.builder()
                .phone("1234567890")
                .name("john_doe")
                .password("password")
                .isVerified(true)
                .build();
    }

    private EmployeePostDto createEmployeePostDtoSample() {
        return EmployeePostDto.builder()
                .phone("1234567890")
                .name("john_doe")
                .password("password")
                .build();
    }

    private EmployeeInfoDto createEmployeeInfoDtoSample() {
        return EmployeeInfoDto.builder()
                .id(1L)
                .phone("1234567890")
                .name("john_doe")
                .build();
    }

    private EmployeePutDto createEmployeePutDtoSample() {
        return EmployeePutDto.builder()
                .phone("1234567890")
                .name("john_doe")
                .password("new_password")
                .build();
    }
}
