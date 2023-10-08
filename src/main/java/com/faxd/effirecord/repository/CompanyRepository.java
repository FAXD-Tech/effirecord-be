package com.faxd.effirecord.repository;

import com.faxd.effirecord.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends BaseVerifiedRepository<Company, Long> {

}
