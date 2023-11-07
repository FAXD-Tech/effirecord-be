package com.faxd.effirecord.repository;

import com.faxd.effirecord.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface EmployeeRepository extends BaseVerifiedRepository<Employee, Long> {
    Optional<List<Employee>> findAllByIdInAndCurrentProjectIdAndIsDeletedFalse(Set<Long> employeeIds, Long projectId);
}
