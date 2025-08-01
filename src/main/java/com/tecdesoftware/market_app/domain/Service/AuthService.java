package com.tecdesoftware.market_app.domain.Service;

import com.tecdesoftware.market_app.persistance.crud.ClienteCrudRepository;
import com.tecdesoftware.market_app.config.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private ClienteCrudRepository clienteRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public String login(String correo, String contrasena) {
        return clienteRepository.findByCorreoElectronico(correo)
                .filter(cliente -> passwordEncoder.matches(contrasena, cliente.getContrasena()))
                .map(cliente -> jwtUtil.generateToken(cliente.getCorreoElectronico()))
                .orElse(null);
    }
}