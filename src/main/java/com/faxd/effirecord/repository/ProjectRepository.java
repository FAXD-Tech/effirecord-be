package com.faxd.effirecord.repository;

import com.faxd.effirecord.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectRepository extends BaseVerifiedRepository<Project, Long> {

    Optional<Project> findByIdAndIsDeletedFalseAndIsVerifiedTrue(Long projectId);
}
