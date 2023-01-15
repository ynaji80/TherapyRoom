package com.s5project.therapyroom.user.dto;

import lombok.Data;

@Data
public class UpdatePatientRequest {
   private String phone;
   private String address;

   public UpdatePatientRequest(){}

    public UpdatePatientRequest(String phone,String address){
       this.phone=phone;
       this.address=address;
    }

    public String getPhone(){
       return phone;
    }
    public String getAddress(){
       return address;
    }

    public void setPhone(String Phone){
       phone=Phone;
    }
    public void setAddress(String Address){
       address=Address;
    }

}
