package com.emre.controller.impl;

import com.emre.dto.*;
import com.emre.entities.RefreshToken;
import com.emre.entities.User;
import com.emre.security.JwtTokenProvider;
import com.emre.service.IUserService;
import com.emre.service.impl.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")

public class AuthenticationController {

    @Autowired
    private  AuthenticationManager authenticationManager;

    @Autowired
    private  JwtTokenProvider jwtTokenProvider;

    @Autowired
    private  IUserService userService;

    @Autowired
    private  PasswordEncoder passwordEncoder;

    @Autowired
    private  RefreshTokenService refreshTokenService;


    @PostMapping("/login")
    public AuthResponse login(@RequestBody UserRequest loginRequest){
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(), loginRequest.getPassword());
        Authentication auth = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(auth);
        String jwtToken = jwtTokenProvider.generateJwtToken(auth);
        DtoUser dtoUser = userService.getOneUserByUsername(loginRequest.getUsername());
        dtoUser.toUser();
        AuthResponse authResponse = new AuthResponse();
        authResponse.setAccessToken("Bearer " + jwtToken);
        authResponse.setRefreshToken(refreshTokenService.createRefreshToken(dtoUser.toUser()));
        authResponse.setUserId(dtoUser.getId());
        return authResponse;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody UserRequest registerRequest){
        AuthResponse authResponse= new AuthResponse();

        if (userService.getOneUserByUsername(registerRequest.getUsername()) != null){
            authResponse.setMessage("Username already in use.");
            return new ResponseEntity<>(authResponse, HttpStatus.BAD_REQUEST);
        }

        // User kaydı
        DtoUserIU dtoUser = new DtoUserIU();
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        BeanUtils.copyProperties(user, dtoUser);
        userService.saveUser(dtoUser);

        // Auth işlemi
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(registerRequest.getUsername(), registerRequest.getPassword());
        Authentication auth = authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(auth);

        // JWT ve refresh token oluştur
        String jwtToken = jwtTokenProvider.generateJwtToken(auth);
        DtoUser savedUser = userService.getOneUserByUsername(registerRequest.getUsername());
        String refreshToken = refreshTokenService.createRefreshToken(savedUser.toUser());

        // Response ayarla
        authResponse.setMessage("User registered successfully.");
        authResponse.setAccessToken("Bearer " + jwtToken);
        authResponse.setRefreshToken(refreshToken);
        authResponse.setUserId(savedUser.getId());

        return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refresh(@RequestBody RefreshTokenRequest request){
        AuthResponse response = new AuthResponse();
        RefreshToken token = refreshTokenService.getByUserId(request.getUserId());
        //if(token == null){
        //    response.setMessage("Refresh token not found for this user.");
       //     return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
       // }

        if(token.getToken().equals(request.getRefreshToken()) && !refreshTokenService.isRefreshTokenExpired(token)) {
            User user = token.getUser();
            String jwtToken = jwtTokenProvider.generateJwtTokenByUserId(user.getId());
            response.setMessage("Token successfully refreshed.");
            response.setAccessToken("Bearer " + jwtToken);
            response.setUserId(user.getId());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else{
            response.setMessage("Refresh token is not valid.");
            return new ResponseEntity<>(response,HttpStatus.UNAUTHORIZED);
        }
    }

}
