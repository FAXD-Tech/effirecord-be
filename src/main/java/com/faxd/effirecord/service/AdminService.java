package com.faxd.effirecord.service;

import com.faxd.effirecord.exception.CompanyNotFoundException;
import com.faxd.effirecord.model.Company;
import com.faxd.effirecord.repository.CompanyRepository;
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
public class AdminService {
    private final CompanyRepository companyRepository;
    private final Logger logger = LoggerFactory.getLogger(AdminService.class);

    public void verifyCompany(Long id){
        Optional<Company> company = companyRepository.findById(id);
        company.ifPresentOrElse(c -> c.setVerified(true),
                () -> {
                    throw new CompanyNotFoundException(id);
                } );
    }

    public void deleteCompany(Long id) {
        companyRepository.deleteById(id);
    }
}
