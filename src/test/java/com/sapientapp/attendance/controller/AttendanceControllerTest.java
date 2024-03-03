package com.sapientapp.attendance.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.sapientapp.attendance.model.Attendance;
import com.sapientapp.attendance.service.AttendancePersistenceService;

@SpringBootTest
public class AttendanceControllerTest {

    @Mock
    private AttendancePersistenceService attendancePersistenceService;

    @InjectMocks
    private AttendanceController attendanceController;

    @Test
    public void testAttendanceStatusSuccess() {
        
        Long employeeId = 123L;
        LocalDate date = LocalDate.now();
        Attendance mockedAttendance = new Attendance();
        mockedAttendance.setStatus("Present");

      
        when(attendancePersistenceService.attendanceStatus(employeeId, date)).thenReturn(mockedAttendance);

       
        ResponseEntity<String> responseEntity = attendanceController.attendanceStatus(employeeId, date);

   
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Present", responseEntity.getBody());
    }

    @Test
    public void testAttendanceStatusException() {
        
        Long employeeId = 456L;
        LocalDate date = LocalDate.now();

      
        when(attendancePersistenceService.attendanceStatus(employeeId, date)).thenThrow(new RuntimeException("Employee Id not found"));

        
        ResponseEntity<String> responseEntity = attendanceController.attendanceStatus(employeeId, date);

     
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody().contains("No matching employeeId or date found"));
    }
}
