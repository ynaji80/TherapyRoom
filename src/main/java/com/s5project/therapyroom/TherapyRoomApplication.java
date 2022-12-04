package com.s5project.therapyroom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
