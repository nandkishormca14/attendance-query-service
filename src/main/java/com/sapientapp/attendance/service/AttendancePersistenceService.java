package com.sapientapp.attendance.service;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapientapp.attendance.dto.AttendanceStatusDTO;
import com.sapientapp.attendance.model.Attendance;
import com.sapientapp.attendance.repository.AttendanceRepository;

@Service
public class AttendancePersistenceService {
    private static final Logger logger = LoggerFactory.getLogger(AttendancePersistenceService.class);

    private final AttendanceRepository attendanceRepository;

    @Autowired
    public AttendancePersistenceService(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    public void saveAttendanceStatus(AttendanceStatusDTO attendanceStatusDTO) {
        try {
            Attendance attendanceResponse = attendanceRepository.findByEmployeeIdAndDate(attendanceStatusDTO.getEmployeeId(), attendanceStatusDTO.getDate());

            if (attendanceResponse != null) {
                attendanceResponse.setStatus(attendanceStatusDTO.getStatus());
                attendanceRepository.save(attendanceResponse);
            } else {
                Attendance attendance = new Attendance();
                attendance.setEmployeeId(attendanceStatusDTO.getEmployeeId());
                attendance.setDate(attendanceStatusDTO.getDate());
                attendance.setStatus(attendanceStatusDTO.getStatus());
                attendanceRepository.save(attendance);
            }
        } catch (Exception e) {
            logger.error("Error saving attendance status: {}", e.getMessage());
            throw new RuntimeException("Error saving attendance status", e);
        }
    }

    public Attendance attendanceStatus(Long employeeId, LocalDate date) {
        try {
            Attendance attendance = attendanceRepository.findByEmployeeIdAndDate(employeeId, date);
            if(attendance!=null) {
            	return attendance;
            }
            
        } catch (Exception e) {
            logger.error("Error getting attendance status: {}", e.getMessage());
            throw new RuntimeException("No matching employeeId or date found ");           
        }
        return null;
    }
}
