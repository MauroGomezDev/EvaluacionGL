package com.globallogic.evaluacion.jwt;

import com.globallogic.evaluacion.model.User;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private String id;
    private LocalDate created;
    private LocalDate lastLogin;
    private String token;
    private boolean isActive;

    // Constructor
    //public UserResponse(String id, LocalDate created, LocalDate lastLogin, String token, boolean isActive) {

    public UserResponse(User user) {
        this.id = user.getId();
        this.created = user.getCreated();
        this.lastLogin = user.getLastLogin();
        this.token = user.getToken();
        this.isActive = user.isActive();
    }

 }
