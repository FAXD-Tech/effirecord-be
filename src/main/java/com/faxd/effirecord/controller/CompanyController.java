package com.faxd.effirecord.controller;

import com.faxd.effirecord.dto.company.CompanyInfoDto;
import com.faxd.effirecord.dto.company.CompanyPostDto;
import com.faxd.effirecord.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("company")
@RequiredArgsConstructor
@Validated
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping("")
    public CompanyInfoDto register(@RequestBody CompanyPostDto companyPostDto){
        return companyService.register(companyPostDto);
    }

    @DeleteMapping("/{id}")
    public void deleteCompany(@PathVariable Long id){
        companyService.deleteCompany(id);
    }
}
