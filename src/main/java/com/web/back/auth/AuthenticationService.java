package com.web.back.auth;


import com.web.back.Entities.Role;
import com.web.back.Entities.User;
import com.web.back.config.JwtService;
import com.web.back.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthenticationService {


    private final UsuarioRepository userRepository;


    private final PasswordEncoder passwordEncoder;


    private final JwtService jwtService;


    private AuthenticationManager authenticationManager;

    public String register(User registerRequest) {
        var user = User.builder()
                .name(registerRequest.getName())
                .email(registerRequest.getEmail())
                .telefono(registerRequest.getTelefono()
                ).password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        var jwt = jwtService.generateToken(user);
        return jwt;
    }
    public AuthenticationRequest authenticate(AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getEmail(),
                        authenticationRequest.getPassword()
                )
        );
        var user = userRepository.findByEmail(authenticationRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
        var jwt = jwtService.generateToken(user);
        return authenticationRequest.builder()
                .token(jwt)
                .build();
    }
}
