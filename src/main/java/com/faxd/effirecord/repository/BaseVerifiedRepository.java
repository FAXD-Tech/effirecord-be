package com.faxd.effirecord.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface BaseVerifiedRepository<T, ID> extends JpaRepository<T, ID> {
    Optional<T> findByIdAndIsDeletedFalseAndIsVerifiedTrue(ID projectId);
}
