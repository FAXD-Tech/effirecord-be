package com.faxd.effirecord.repository;

import com.faxd.effirecord.model.BaseEntity;
import com.faxd.effirecord.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends BaseVerifiedRepository<Employee, Long> {

}
