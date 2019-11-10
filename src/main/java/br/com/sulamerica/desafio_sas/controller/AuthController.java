package br.com.sulamerica.desafio_sas.controller;

import static org.springframework.http.ResponseEntity.ok;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.sulamerica.desafio_sas.component.JwtTokenProvider;
import br.com.sulamerica.desafio_sas.entity.Usuario;
import br.com.sulamerica.desafio_sas.repository.UsuarioRepository;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    UsuarioRepository repo;

    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public ResponseEntity<Object> signin(@RequestBody Usuario data) {

    	try {
            String username = data.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));

            String perfil = this.repo.findByNome(username).getPerfil().getNome();
            List<String> roles = new ArrayList<>();
            roles.add(perfil);

            String token = jwtTokenProvider.createToken(username, roles);
            Map<Object, Object> model = new HashMap<>();
            model.put("username", username);
            model.put("token", token);

            return ok(model);

        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/password supplied");
        }
    }
}