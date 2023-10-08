package com.faxd.effirecord.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "projects")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Project extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    @JsonIgnore
    private Company company;

    @Column(nullable = false)
    private String name;

    @Column(name = "is_verified")
    @Builder.Default
    private boolean isVerified = false;

    @OneToMany(mappedBy = "currentProject")
    private List<Employee> employees;
}
