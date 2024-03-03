package com.sapientapp.attendance.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.sapientapp.attendance.dto.AttendanceStatusDTO;
import com.sapientapp.attendance.service.AttendancePersistenceService;

@Component
public class KafkaConsumer {
    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    @Autowired
    private AttendancePersistenceService attendancePersistenceService;

    @KafkaListener(topics = "attendance-event-topic", groupId = "attendance-event-topic-group")
    public void consumeAttendanceEvent(AttendanceStatusDTO attendanceStatusDTO) {
        try {
            logger.info("Consumed attendance event: {}", attendanceStatusDTO.toString());
            attendancePersistenceService.saveAttendanceStatus(attendanceStatusDTO);
        } catch (Exception e) {
            logger.error("Error consuming attendance event: {}", e.getMessage());
        }
    }
}
