package com.faxd.effirecord.database;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class DatabaseManagementService {

    private final JdbcTemplate jdbcTemplate;

    /**
     * \
     * @param attendanceDate
     */
    public void createNewWorkHoursPartition( LocalDate attendanceDate) {
        String attendanceMonth = attendanceDate.format(DateTimeFormatter.ofPattern("yyyy-MM"));
        String insertDateMonth = attendanceDate.format(DateTimeFormatter.ofPattern("yyyy_MM"));
        String partitionTableName = "work_hours_" + insertDateMonth;
        String sql = "SELECT EXISTS (SELECT 1 FROM information_schema.tables WHERE table_name = ?)";
        Boolean b = jdbcTemplate.queryForObject(sql, Boolean.class, partitionTableName);
        if (!b) {
            String createPartitionSQL = "CREATE TABLE " + partitionTableName +
                    " PARTITION OF work_hours FOR VALUES FROM ('" + attendanceMonth + "-01') TO ('" + getEndOfMonth(attendanceDate) + "')";

            try {
                jdbcTemplate.execute(createPartitionSQL);
                // 分区表创建成功
            } catch (Exception e) {
                // 分区表创建失败，处理异常
                e.printStackTrace();
            }
        }
    }

    private LocalDate getEndOfMonth(LocalDate attendanceDate){
        YearMonth yearMonth = YearMonth.of(attendanceDate.getYear(), attendanceDate.getMonthValue());
        return yearMonth.atEndOfMonth();
    }
}
