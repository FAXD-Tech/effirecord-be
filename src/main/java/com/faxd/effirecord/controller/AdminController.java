package com.faxd.effirecord.controller;

import com.faxd.effirecord.service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin")
@RequiredArgsConstructor
@Validated
@Tag(name = "admin", description = "Admin APIs")
public class AdminController {

    private final AdminService adminService;

    @Operation(
            summary = "Verify a company by Id",
            description = "Verify a Company object by specifying its id. The response is none when is successful or throw an error")
    @GetMapping("/company/{id}/verify")
    @PreAuthorize("hasPermission(#id,'admin','verify')")
    public void verifyCompanyById(@PathVariable Long id){
        adminService.verifyCompany(id);
    }

    @Operation(
            summary = "Delete a company by Id",
            description = "Delete a Company object by specifying its id. The response is none when is successful or throw an error")
    @DeleteMapping("/company/{id}/delete")
    @PreAuthorize("hasPermission(#id,'super-admin','delete')")
    public void deleteCompanyById(@PathVariable Long id){
        adminService.deleteCompany(id);
    }

}
