package com.s5project.therapyroom.user.dto;

import com.s5project.therapyroom.user.domain.AppointmentStatus;
import lombok.Data;

@Data
public class AppointmentDto {
    private Long doctorId;
    private Long patientId;
    private String notes;
    private AppointmentStatus status;
}
