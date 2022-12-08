package com.s5project.therapyroom.user.api.controllers;

import com.s5project.therapyroom.user.domain.Role;
import com.s5project.therapyroom.user.domain.User;
import com.s5project.therapyroom.user.dto.AuthResponseDto;
import com.s5project.therapyroom.user.dto.LoginDto;
import com.s5project.therapyroom.user.dto.RegisterDto;
import com.s5project.therapyroom.user.dto.UserResponse;
import com.s5project.therapyroom.user.repository.RoleRepo;
import com.s5project.therapyroom.user.security.JWTGenerator;
import com.s5project.therapyroom.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:8081")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepo roleRepo;
    private final JWTGenerator jwtGenerator;


    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
        if(userService.userExits(registerDto.getUsername())){
            return new ResponseEntity<>("username already exists", HttpStatus.BAD_REQUEST);
        }
        User user = new User();
        user.setUsername(registerDto.getUsername());
        user.setName(registerDto.getName());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        Role roles = roleRepo.findByName("ROLE_USER");
        user.setRoles(Collections.singletonList(roles));
        userService.saveUser(user);
        return new ResponseEntity<>("User registeration is successful", HttpStatus.OK);

    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginDto loginDto){
        Authentication authentication =  authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(),
                loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        User user = userService.getUser(loginDto.getUsername());
        UserResponse currentUser = new UserResponse(user.getName(),user.getUsername(),
                user.getRoles().stream().map(role -> new String(role.getName())).collect(Collectors.toList()));
        return new ResponseEntity<>(new AuthResponseDto(token,currentUser) , HttpStatus.OK);
    }
}
