package com.globallogic.evaluacion.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.globallogic.evaluacion.exceptions.InvalidDataException;
import com.globallogic.evaluacion.exceptions.ResponseException;
import com.globallogic.evaluacion.exceptions.UserAlreadyExistsException;
import com.globallogic.evaluacion.jwt.UserResponse;
import com.globallogic.evaluacion.model.User;
import com.globallogic.evaluacion.service.UserService;
import com.globallogic.evaluacion.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/api/evaluacion")
//@CrossOrigin("*")
//@CrossOrigin(origins = "http://localhost:8080")
//@ComponentScan(basePackages = "com.globallogic")
public class EvaluacionController {

    private static final Logger log = LoggerFactory.getLogger(EvaluacionController.class);

    @Autowired
    private UserService userService;

    public EvaluacionController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/sign-up", consumes="application/json", produces = { "*/*" })
    public ResponseEntity<?> signUp(@RequestBody User user) throws JsonProcessingException {
        log.info("Procesando "+user);
        User newUser = userService.createUser(user);
        log.info("Usuario Creado OK "+newUser);
        UserResponse userResponse = new UserResponse(newUser);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Utils.mapToJson(userResponse));
    }

    @GetMapping(value = "/list")
    public List<User> getAllUsers(){
        return userService.listar();
    }
}
