package com.faxd.effirecord.repository;

import com.faxd.effirecord.model.Project;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends BaseVerifiedRepository<Project, Long> {

}
