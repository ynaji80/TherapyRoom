package com.s5project.therapyroom;

import com.s5project.therapyroom.domain.Role;
import com.s5project.therapyroom.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TherapyRoomApplication {

    public static void main(String[] args) {
        SpringApplication.run(TherapyRoomApplication.class, args);
    }

//    @Bean
//    CommandLineRunner run(UserService userService){
//        return args -> {
//            userService.saveRole(new Role(null, "ROLE_USER"));
//            userService.saveRole(new Role(null, "ROLE_ADMIN"));
//        };
//    }

//            userService.saveUser(new User(null, "John Doe","john", "qwerty1234", new ArrayList<>()));
//            userService.saveUser(new User(null, "Youness Naji","ynaji", "qwerty1234", new ArrayList<>()));
//            userService.saveUser(new User(null, "Hamza bessa","hbessa", "qwerty1234", new ArrayList<>()));
//            userService.saveUser(new User(null, "Oublal mhm","mhm", "qwerty1234", new ArrayList<>()));
//
//            userService.addRoleToUser("john", "ROLE_USER");
//            userService.addRoleToUser("ynaji", "ROLE_USER");
//            userService.addRoleToUser("ynaji", "ROLE_ADMIN");
//            userService.addRoleToUser("ynaji", "ROLE_SUPER_ADMIN");
//            userService.addRoleToUser("mhm", "ROLE_MANAGER");
//
//        };
//    }
}
