package com.sapientapp.attendance.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sapientapp.attendance.dto.AttendanceStatusDTO;
import com.sapientapp.attendance.model.Attendance;
import com.sapientapp.attendance.repository.AttendanceRepository;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class AttendancePersistenceServiceTest {

    @Mock
    private AttendanceRepository attendanceRepository;

    @InjectMocks
    private AttendancePersistenceService attendancePersistenceService;

    @Test
    public void testSaveAttendanceStatus() {
       
        AttendanceStatusDTO attendanceStatusDTO = new AttendanceStatusDTO();
        attendanceStatusDTO.setEmployeeId(123L);
        attendanceStatusDTO.setDate(LocalDate.now());
        attendanceStatusDTO.setStatus("Present");

        when(attendanceRepository.findByEmployeeIdAndDate(any(), any())).thenReturn(null);

        attendancePersistenceService.saveAttendanceStatus(attendanceStatusDTO);

        verify(attendanceRepository, times(1)).save(any(Attendance.class));
    }

    @Test
    public void testAttendanceStatusFound() {
      
        Long employeeId = 123L;
        LocalDate date = LocalDate.now();
        Attendance mockedAttendance = new Attendance();
        mockedAttendance.setStatus("Present");
       
        when(attendanceRepository.findByEmployeeIdAndDate(employeeId, date)).thenReturn(mockedAttendance);

        Attendance result = attendancePersistenceService.attendanceStatus(employeeId, date);

        assertNotNull(result);
        assertEquals("Present", result.getStatus());
    }


}
