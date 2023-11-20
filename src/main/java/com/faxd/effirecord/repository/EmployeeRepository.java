package com.faxd.effirecord.repository;

import com.faxd.effirecord.model.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface EmployeeRepository extends BaseVerifiedRepository<Employee, Long> {
    Optional<List<Employee>> findAllByIdInAndCurrentProjectIdAndIsDeletedFalse(Set<Long> employeeIds, Long projectId);

    Optional<Employee> findByPhoneAndIsDeletedFalseAndIsVerifiedTrue(String nameOrPhone);

    Optional<Employee> findByNameAndIsDeletedFalseAndIsVerifiedTrue(String name);

    @Query("SELECT e FROM Employee e" +
            " WHERE (e.name = :name OR e.phone = :phone) " +
            "AND e.isDeleted = false AND e.isVerified = true")
    Optional<Employee> findByNameOrPhone(String name, String phone);
}
