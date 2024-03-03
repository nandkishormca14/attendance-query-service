package com.sapientapp.attendance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sapientapp.attendance.model.Attendance;
import java.lang.Long;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    Attendance findByEmployeeIdAndDate(Long employeeId, LocalDate date);
}



