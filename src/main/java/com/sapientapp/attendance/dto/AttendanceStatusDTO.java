package com.sapientapp.attendance.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class AttendanceStatusDTO {

	private Long employeeId;
    private LocalDate date;
    private String status;
	@Override
	public String toString() {
		return "AttendanceStatusDTO [employeeId=" + employeeId + ", date=" + date + ", status=" + status + "]";
	}
}
