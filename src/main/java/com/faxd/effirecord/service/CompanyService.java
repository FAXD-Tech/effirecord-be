package com.faxd.effirecord.service;

import com.faxd.effirecord.dto.company.CompanyInfoDto;
import com.faxd.effirecord.dto.company.CompanyPostDto;
import com.faxd.effirecord.exception.CompanyNotFoundException;
import com.faxd.effirecord.mapper.CompanyMapper;
import com.faxd.effirecord.model.Company;
import com.faxd.effirecord.repository.CompanyRepository;
import com.faxd.effirecord.repository.ProjectRepository;
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
public class CompanyService {
    private final Logger logger = LoggerFactory.getLogger(CompanyService.class);
    private final CompanyRepository companyRepository;
    private final  CompanyMapper companyMapper;
    private final ProjectRepository projectRepository;

    public CompanyInfoDto register(CompanyPostDto companyPostDto){
        Company company = companyMapper.companyPostDtoToCompany(companyPostDto);
        Company saveCompany = companyRepository.save(company);
        return companyMapper.companyToCompanyInfoDto(saveCompany);
    }

    public void deleteCompany(Long id) {
        Optional<Company> company = companyRepository.findById(id);
        company.ifPresent(company1 -> company1.setIsDeleted(true));
    }

    public void verifyProject(Long id) {
        projectRepository.findById(id).ifPresent(project -> project.setVerified(true));
    }

    public CompanyInfoDto getCompanyById(Long id) {
        return companyRepository.findById(id)
                .map(companyMapper::companyToCompanyInfoDto)
                .orElseThrow(() -> new CompanyNotFoundException(id));
    }
}
