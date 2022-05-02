package ar.edu.unq.desaapp.grupo.a.backenddesaappapi.services;

import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.User;
import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.dto.UserDto;
import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.repositories.UserRepository;
import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.util.UserRegisterChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Transactional
    public UserDto save(UserDto userDto){
        User user = new User();
        user.fromDto(userDto);
        UserRegisterChecker.userRegisterValid(user);
        return this.userRepository.save(user).toDto();
    }

    @Transactional
    public List<UserDto> getAll() {
        return userRepository.findAll().stream().map(User::toDto).collect(Collectors.toList());
    }
}
