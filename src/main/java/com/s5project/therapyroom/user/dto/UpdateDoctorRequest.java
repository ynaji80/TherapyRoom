package com.s5project.therapyroom.user.dto;

import com.s5project.therapyroom.user.domain.DoctorSpeciality;
import lombok.Data;

@Data
public class UpdateDoctorRequest {
    private String address;
    private String phone;
    private DoctorSpeciality doctorSpeciality;

    public UpdateDoctorRequest(){}

    public UpdateDoctorRequest( String address, String phone, DoctorSpeciality doctorSpeciality) {
        this.address = address;
        this.phone = phone;
        this.doctorSpeciality = doctorSpeciality;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public DoctorSpeciality getDoctorSpeciality() {
        return doctorSpeciality;
    }

    public void setDoctorSpeciality(DoctorSpeciality doctorSpeciality) {
        this.doctorSpeciality = doctorSpeciality;
    }
}
