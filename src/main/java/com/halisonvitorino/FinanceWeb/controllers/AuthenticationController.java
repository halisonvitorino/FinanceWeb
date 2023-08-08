package com.halisonvitorino.FinanceWeb.controllers;

import com.halisonvitorino.FinanceWeb.dto.AuthenticationDTO;
import com.halisonvitorino.FinanceWeb.dto.LoginResponseDTO;
import com.halisonvitorino.FinanceWeb.dto.RegisterDTO;
import com.halisonvitorino.FinanceWeb.models.Usuario;
import com.halisonvitorino.FinanceWeb.repository.UsuarioRep;
import com.halisonvitorino.FinanceWeb.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UsuarioRep usuarioRep;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data) {
         UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                 new UsernamePasswordAuthenticationToken(data.getLogin(),data.getPassword());
         var auth = this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
         var token = tokenService.gerarToken((Usuario) auth.getPrincipal());
         return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/novoUsuario")
    public ResponseEntity novoUsuario(@RequestBody @Valid RegisterDTO data) {
        if(this.usuarioRep.findByLogin(data.getLogin()) != null) return ResponseEntity.badRequest().build();
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.getPassword());
        Usuario usuario = new Usuario(data.getLogin(), encryptedPassword, data.getRole());
        this.usuarioRep.save(usuario);
        return ResponseEntity.ok().build();
    }
}
