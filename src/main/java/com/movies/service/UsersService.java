package com.movies.service;

import com.movies.domain.Users;
import com.movies.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.beans.Encoder;

@Service
public class UsersService {

    @Autowired
    UsersRepository userRepo;

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    JwtService jwtService;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public Users register(Users user) {

        user.setPassword(encoder.encode(user.getPassword()));
        userRepo.save(user);
        return user;
    }


    public String verify(Users user) {

        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        if (authentication.isAuthenticated())
            return jwtService.generateToken(user.getUsername());
        return "fail";
    }
}
