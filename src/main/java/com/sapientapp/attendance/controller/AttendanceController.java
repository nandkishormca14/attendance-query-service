package com.sapientapp.attendance.controller;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sapientapp.attendance.model.Attendance;
import com.sapientapp.attendance.service.AttendancePersistenceService;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {
    private static final Logger logger = LoggerFactory.getLogger(AttendanceController.class);

    @Autowired
    private AttendancePersistenceService attendancePersistenceService;

    @GetMapping("/attendance-status/{employeeId}/{date}")
    public ResponseEntity<String> attendanceStatus(
            @PathVariable Long employeeId,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        try {
            Attendance attendance = attendancePersistenceService.attendanceStatus(employeeId, date);
            return ResponseEntity.ok(attendance.getStatus());
        } catch (Exception e) {
            logger.error("Error getting attendance status: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No matching employeeId or date found "); 
        }
    }
}
