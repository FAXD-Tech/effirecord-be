package com.faxd.effirecord.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "companies")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Company extends BaseEntity{

    @Column(unique = true, nullable = false)
    private String name;

    @Column(name = "is_verified")
    @Builder.Default
    private boolean isVerified = false;

    @OneToMany(mappedBy = "company",fetch = FetchType.LAZY)
    private List<Project> projects;
}
