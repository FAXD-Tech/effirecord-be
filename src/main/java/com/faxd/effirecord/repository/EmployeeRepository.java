package com.faxd.effirecord.repository;

import com.faxd.effirecord.model.Employee;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends BaseVerifiedRepository<Employee, Long> {

}
