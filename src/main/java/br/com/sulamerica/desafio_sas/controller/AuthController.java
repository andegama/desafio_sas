package br.com.sulamerica.desafio_sas.controller;

import static org.springframework.http.ResponseEntity.*;

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
import br.com.sulamerica.desafio_sas.exceptions.InactiveUserException;
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
            String userName = data.getUsername();

            if (!repo.isActive(userName)) {
            	throw new InactiveUserException("Usuário não está ativo.");
            }

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, data.getPassword()));

            String perfil = this.repo.findByNome(userName).getPerfil().getNome();
            List<String> roles = new ArrayList<>();
            roles.add(perfil);

            String token = jwtTokenProvider.createToken(userName, roles);
            Map<Object, Object> model = new HashMap<>();
            model.put("username", userName);
            model.put("token", token);

            return ok(model);

        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Nome de usuário/senha inválidos");
        }
    }
}