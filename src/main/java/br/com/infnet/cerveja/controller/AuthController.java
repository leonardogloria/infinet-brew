package br.com.infnet.cerveja.controller;


import br.com.infnet.cerveja.dto.LoginDTO;
import br.com.infnet.cerveja.dto.TokenDTO;
import br.com.infnet.cerveja.model.Perfil;
import br.com.infnet.cerveja.model.User;
import br.com.infnet.cerveja.repository.PerfilRepository;
import br.com.infnet.cerveja.repository.UserRepository;
import br.com.infnet.cerveja.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PerfilRepository perfilRepository;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<TokenDTO> auth(@RequestBody @Validated LoginDTO loginDTO) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(loginDTO.getUser(), loginDTO.getPass());
        Authentication authenticate = authenticationManager.authenticate(authToken);
        String token = tokenService.generateToken(authenticate);
        TokenDTO dto = TokenDTO.builder().type("Bearer").token(token).build();
        return ResponseEntity.ok(dto);

    }

    @GetMapping
    public void createUser() {
        Optional<Perfil> byId = perfilRepository.findById(1);

        User user = new User();
        user.setEmail("leonardo2");
        user.setPass(new BCryptPasswordEncoder().encode("123456"));
        user.setPerfis(Set.of(byId.get()));
        userRepository.save(user);

    }

}
