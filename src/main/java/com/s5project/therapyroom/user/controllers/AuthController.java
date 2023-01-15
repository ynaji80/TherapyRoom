package com.s5project.therapyroom.user.controllers;

import com.s5project.therapyroom.user.domain.Patient;
import com.s5project.therapyroom.user.domain.User;
import com.s5project.therapyroom.user.domain.UserType;
import com.s5project.therapyroom.user.dto.*;
import com.s5project.therapyroom.user.security.JWTGenerator;
import com.s5project.therapyroom.user.service.PatientService;
import com.s5project.therapyroom.user.service.UserService;
import com.s5project.therapyroom.user.specs.PatientSpec;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.header.Header;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/api/auth")
//@CrossOrigin(origins = "http://localhost:8081")
//@CrossOrigin(origins = "http://10.1.10.72:3000")
@CrossOrigin

@RequiredArgsConstructor
@Slf4j

public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
//    private final PatientService patientService;
    private final PasswordEncoder passwordEncoder;
    private final JWTGenerator jwtGenerator;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDto> register(@RequestBody RegisterDto registerDto){
        if(userService.userExits(registerDto.getUsername())){
            RegisterResponseDto registerResponseDto = new RegisterResponseDto();
            registerResponseDto.setMessage("User already exists");
            return new ResponseEntity<>(registerResponseDto, HttpStatus.BAD_REQUEST);
        }
        User user = new User();
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setType(registerDto.getType());
        log.info("this is user {}", user);
        log.info("this is user {}", user.getType());
        RegisterResponseDto registerResponseDto = new RegisterResponseDto();
        User user1 = userService.saveUser(user);
        registerResponseDto.setUserId(user1.getId());
        registerResponseDto.setUsername(user1.getUsername());
        registerResponseDto.setMessage("User registeration is successful");
        if ((registerDto.getType().equals(UserType.DOCTOR))) {
            userService.addRoleToUser(user.getUsername(), "ROLE_DOCTOR");
        } else if((registerDto.getType().equals(UserType.PATIENT)))  {
            userService.addRoleToUser(user.getUsername(), "ROLE_PATIENT");
        }else {
            userService.addRoleToUser(user.getUsername(), "ROLE_ADMIN");
        }
        return new ResponseEntity<>(registerResponseDto, HttpStatus.CREATED);

    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginDto loginDto, HttpServletRequest request,
                                                 HttpServletResponse response) throws IOException {
        Authentication authentication =  authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(),
                loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        Cookie cookie = new Cookie("authorization", token);
        cookie.setPath("/");
        response.addCookie(cookie);
        User user = userService.getUser(loginDto.getUsername());
        UserResponse currentUser = new UserResponse(user.getUsername(),
                user.getRoles().stream().map(role -> new String(role.getName())).collect(Collectors.toList()));
        return new ResponseEntity<>(new AuthResponseDto(token,currentUser) , HttpStatus.OK);
    }
}
