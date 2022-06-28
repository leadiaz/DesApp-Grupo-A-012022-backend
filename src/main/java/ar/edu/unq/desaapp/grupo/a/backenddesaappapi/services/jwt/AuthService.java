package ar.edu.unq.desaapp.grupo.a.backenddesaappapi.services.jwt;

import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.user.dto.LoginUserDto;
import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.security.JwtTokenUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtUserDetailsService jwtUserDetailsService;
    private final JwtTokenUtil jwtTokenUtil;

    public AuthService(AuthenticationManager authenticationManager, JwtUserDetailsService jwtUserDetailsService, JwtTokenUtil jwtTokenUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUserDetailsService = jwtUserDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
    }
    public String createAuthenticationToken(LoginUserDto authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());
        final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(authenticationRequest.getEmail());
        return jwtTokenUtil.generateToken(userDetails);
    }

    private void authenticate(String email, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        }catch (DisabledException e){
            throw new Exception("User disabled", e);
        }catch (BadCredentialsException e){
            System.out.println(email+" "+password);
            throw new Exception("Invalid credentials", e);
        }
    }
}