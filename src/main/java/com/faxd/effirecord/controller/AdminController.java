package com.faxd.effirecord.controller;

import com.faxd.effirecord.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin")
@RequiredArgsConstructor
@Validated
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/company/{id}/verify")
    public void verifyCompanyById(@PathVariable Long id){
        adminService.verifyCompany(id);
    }

    @GetMapping("/company/{id}/delete")
    public void deleteCompanyById(@PathVariable Long id){
        adminService.deleteCompany(id);
    }

}
