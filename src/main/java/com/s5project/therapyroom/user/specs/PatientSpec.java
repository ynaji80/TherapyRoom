package com.s5project.therapyroom.user.specs;


import net.kaczmarzyk.spring.data.jpa.domain.EqualIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;

@And({
   @Spec(path="lastname", params = "firstname" , spec= Like.class),
})
public interface PatientSpec {
}
