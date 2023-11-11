package com.globallogic.evaluacion.service;

import com.globallogic.evaluacion.model.User;

import java.util.List;

public interface UserService {

    User createUser(User user);
    String createJwtToken(User user);
    List<User> listar();

}
