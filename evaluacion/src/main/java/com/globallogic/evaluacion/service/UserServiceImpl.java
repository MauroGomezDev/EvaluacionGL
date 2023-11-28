package com.globallogic.evaluacion.service;

import com.globallogic.evaluacion.exceptions.InvalidDataException;
import com.globallogic.evaluacion.exceptions.UserAlreadyExistsException;
import com.globallogic.evaluacion.model.User;
import com.globallogic.evaluacion.repository.UserRepository;
import com.globallogic.evaluacion.util.Utils;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    /**
     * Realiza algunas validaciones antes de llamar al metodo de grabacion
     * @param user incompleto recibido como parametro de la llamada http
     * @return User
     */
    public User createUser(User user) {
        User existingUser = userRepository.findByEmail(user.getEmail());

        if (existingUser != null) {
            throw new UserAlreadyExistsException("Usuario ya existe");
        }

        if (!Utils.isValidEmail(user.getEmail())) {
            throw new InvalidDataException("Formato de correo electrónico incorrecto");
        }

        if (!Utils.isValidPassword(user.getPassword())) {
            throw new InvalidDataException("Formato de contraseña incorrecto");
        }

        return this.saveUser(user);
    }

    /**
     * Crea un el objeto de tipo User para ser grabado en la base de datos
     * @param userRequest recibido como parametro de la llamada http
     * @return User
     */
    public User saveUser(User userRequest) {
        // Generacion de UUID para el id del nuevo usuario
        UUID userId = UUID.randomUUID();

        User user = new User();
        user.setId(userId.toString());
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        user.setPhones(userRequest.getPhones());
        LocalDate dateCreate = LocalDate.now();
        user.setCreated(dateCreate);
        user.setLastLogin(dateCreate);
        user.setActive(true);
        String token = this.createJwtToken(user);
        user.setToken(token);

        return userRepository.save(user);
    }

    /**
     * Crea un token a partir de los datos del usuario
     * @param user
     * @return String token
     */
    public String createJwtToken(User user) {
        // Define las reclamaciones (claims) del token
        Map<String, Object> claims = new HashMap<>();
        claims.put("sub", user.getId());
        claims.put("name", user.getName());
        claims.put("email", user.getEmail());

        // Configura la fecha de emisión y caducidad del token
        ZoneId defaultZoneId = ZoneId.systemDefault();
        LocalDate now = LocalDate.now();
        LocalDate expirationLocalDate = LocalDate.now();

        Date date = Date.from(now.atStartOfDay(defaultZoneId).toInstant());
        Date expirationDate = Date.from(now.atStartOfDay(defaultZoneId).toInstant());

        // Crea el token JWT
        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(date)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, "SecretKey")
                .compact();

        return token;
    }

    /**
     * En este metodo aplicao 2 caracteristicas de java 8 (metodo stream y una expresion lamda)
     * @return
     */
    @Override
    public List<User> listar() {
        List<User> users = userRepository.findAll();
        if (users != null) {
            return users.stream()
                    .sorted((user1, user2) -> {
                        return user1.getName().compareTo(user2.getName());
                    })
                    .collect(Collectors.toList());

        } else {
            return Collections.emptyList();
        }
    }

}
