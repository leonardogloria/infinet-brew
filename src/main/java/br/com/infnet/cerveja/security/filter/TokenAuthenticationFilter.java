package br.com.infnet.cerveja.security.filter;

import br.com.infnet.cerveja.model.User;
import br.com.infnet.cerveja.repository.UserRepository;
import br.com.infnet.cerveja.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class TokenAuthenticationFilter extends OncePerRequestFilter {
    TokenService tokenService;

    UserRepository userRepository;

    public TokenAuthenticationFilter(TokenService tokenService, UserRepository userRepository) {
        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = getTokenFromRequest(request);
        boolean tokenValid = tokenService.isTokenValid(token);
        if(tokenValid){
            this.authenticate(token);
        }
        System.out.printf(String.valueOf(tokenValid));
        filterChain.doFilter(request, response);
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if(token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
            token = null;
        }
        token = token.substring(7, token.length());
        return token;
    }
    private void authenticate(String tokenFromHeader) {
        Integer id = tokenService.getTokenId(tokenFromHeader);

        Optional<User> optionalUser = userRepository.findById(id);

        if(optionalUser.isPresent()) {

            User user = optionalUser.get();

            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user, null, getAuthority(user));
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
    }
    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getPerfis().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        });
        return authorities;
    }
}
