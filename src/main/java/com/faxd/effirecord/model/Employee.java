package com.faxd.effirecord.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "employees")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Employee extends BaseEntity{

    @Column(unique = true, nullable = false)
    private String phone;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @Column(name = "is_verified")
    @Builder.Default
    private boolean isVerified = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "current_project_id")
    @JsonBackReference
    private Project currentProject;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Payroll> payrolls;

    @OneToMany(mappedBy = "employee")
    private List<Card> cards;

    @OneToMany(mappedBy = "employee")
    private List<WorkHours> workHoursList;

//    @OneToMany(mappedBy = "createdBy")
//    private List<WorkHours> createdBy;
//
//    @OneToMany(mappedBy = "updatedBy")
//    private List<WorkHours> updatedBy;

    @ManyToMany(mappedBy = "employees",fetch = FetchType.EAGER)
    private List<Role> roles;
}
