package com.faxd.effirecord.mapper;

import com.faxd.effirecord.dto.company.CompanyInfoDto;
import com.faxd.effirecord.dto.company.CompanyPostDto;
import com.faxd.effirecord.model.Company;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CompanyMapper {
    Company companyPostDtoToCompany(CompanyPostDto companyPostDto);

    CompanyInfoDto companyToCompanyInfoDto(Company company);

}
