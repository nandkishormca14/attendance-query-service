package com.sapientapp.attendance.kafka;

import static org.mockito.Mockito.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.core.KafkaTemplate;

import com.sapientapp.attendance.dto.AttendanceStatusDTO;
import com.sapientapp.attendance.service.AttendancePersistenceService;

@SpringBootTest
public class KafkaConsumerTest {

    private KafkaConsumer kafkaConsumer;

    @MockBean
    private AttendancePersistenceService attendancePersistenceService;

    @MockBean
    private KafkaTemplate<String, AttendanceStatusDTO> kafkaTemplate;

    @BeforeEach
    public void setUp() {
        kafkaConsumer = new KafkaConsumer();
    }

    @Test
    public void testConsumeAttendanceEvent() {
       
        AttendanceStatusDTO attendanceStatusDTO = new AttendanceStatusDTO();
        attendanceStatusDTO.setEmployeeId(123L);
        attendanceStatusDTO.setDate(LocalDate.now());
        attendanceStatusDTO.setStatus("PRESENT");

       
        kafkaConsumer.consumeAttendanceEvent(attendanceStatusDTO);
        attendancePersistenceService.saveAttendanceStatus(attendanceStatusDTO);

        verify(attendancePersistenceService, times(1)).saveAttendanceStatus(attendanceStatusDTO);
    }
}
