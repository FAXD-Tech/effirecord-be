package com.faxd.effirecord.controller;

import com.faxd.effirecord.dto.company.CompanyInfoDto;
import com.faxd.effirecord.dto.company.CompanyPostDto;
import com.faxd.effirecord.service.CompanyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("company")
@RequiredArgsConstructor
@Validated
@Tag(name = "company", description = "company management APIs")
public class CompanyController {

    private final CompanyService companyService;

    @Operation(
            summary = "Create a company",
            description = "Create a Company object. The response is the company information with id")
    @PostMapping("")
    public CompanyInfoDto register(@RequestBody CompanyPostDto companyPostDto){
        return companyService.register(companyPostDto);
    }

    @Operation(
            summary = "Retrieve a company",
            description = "Retrieve a Company object by specifying its id. The response is the company information")
    @GetMapping("/{id}")
    public CompanyInfoDto getCompanyById(@PathVariable Long id){
        return companyService.getCompanyById(id);

    }

    @Operation(
            summary = "Delete a company",
            description = "Delete a Company object by specifying its id.")
    @DeleteMapping("/{id}")
    public void deleteCompany(@PathVariable Long id){
        companyService.deleteCompany(id);
    }

    @Operation(
            summary = "Verify a company",
            description = "Verify a Company object by specifying its id. ")
    @GetMapping("/project/{projectId}/verify")
    public void verifyProjectById(@PathVariable Long projectId){
        companyService.verifyProject(projectId);
    }

}
