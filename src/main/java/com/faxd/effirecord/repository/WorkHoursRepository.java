package com.faxd.effirecord.repository;

import com.faxd.effirecord.model.WorkHours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface WorkHoursRepository extends JpaRepository<WorkHours, Long> {

    List<WorkHours> findAllByEmployeeIdInAndIsDeletedFalseAndAttendanceDateBetween(Set<Long> employeeId, LocalDate start, LocalDate end);

    Optional<WorkHours> findByIdAndIsDeletedFalse(Long id);

    @Query(value = "SELECT w.project.id as projectId, w.partitionKey as month, SUM(w.hoursWorked) as hoursWorkedTotal " +
            "FROM WorkHours as w " +
            "WHERE w.employee.id = :employeeId " +
            "AND w.isDeleted = FALSE " +
            "AND w.attendanceDate BETWEEN :start AND :end " +
            "GROUP BY w.project.id, w.partitionKey")
    List<Object[]> sumWorkedHoursByDateRangeAndPartition(@Param("employeeId") Long employeeId, @Param("start") LocalDate start, @Param("end") LocalDate end);

}
