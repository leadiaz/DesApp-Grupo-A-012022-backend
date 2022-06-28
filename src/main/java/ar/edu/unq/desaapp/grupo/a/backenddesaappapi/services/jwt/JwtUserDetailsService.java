package ar.edu.unq.desaapp.grupo.a.backenddesaappapi.services.jwt;


import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.repositories.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    public JwtUserDetailsService(UserRepository userRepository) {
        this.userRepository= userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.user.User> user= userRepository.findByEmail(email);
        if(user.isPresent()){
            return new User(user.get().getEmail(), user.get().getPassword(), new ArrayList<>());
        }else{
            throw new UsernameNotFoundException("Email does not match");
        }

    }
}