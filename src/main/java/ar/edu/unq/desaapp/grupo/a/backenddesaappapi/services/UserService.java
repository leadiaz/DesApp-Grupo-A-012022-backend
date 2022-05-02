package ar.edu.unq.desaapp.grupo.a.backenddesaappapi.services;

import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.User;
import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.repositories.UserRepository;
import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.util.UserRegisterChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Transactional
    public User save(User user){
        UserRegisterChecker.userRegisterValid(user);
        return this.userRepository.save(user);
    }

    @Transactional
    public List<User> getAll() {
        return userRepository.findAll();
    }
}
