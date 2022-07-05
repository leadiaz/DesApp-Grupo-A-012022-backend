package ar.edu.unq.desaapp.grupo.a.backenddesaappapi.services;

import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.exception.EntityNotFoundException;
import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.user.User;
import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.user.UserInfoOperation;
import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.user.dto.UserInfoOperationDto;
import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.user.dto.UserRegisterDto;
import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.repositories.UserInfoOperationRepository;
import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserInfoOperationService {
    private UserInfoOperationRepository userInfoOperationRepository;
    private UserRepository userRepository;

    @Autowired
    public UserInfoOperationService(UserInfoOperationRepository userInfoOperationRepository, UserRepository userRepository ) {
        this.userInfoOperationRepository = userInfoOperationRepository;
        this.userRepository = userRepository;
    }

    public UserInfoOperation findByUserId(String id) {
        return userInfoOperationRepository.findByUserId(id).orElse(new UserInfoOperation(id));
    }

    public UserInfoOperation save(UserInfoOperation userInfoOperation) {
        return this.userInfoOperationRepository.save(userInfoOperation);
    }


    public List<UserInfoOperationDto> getAllUserInfoOperation() {


        List<UserInfoOperation> allUserInfoOperations = userInfoOperationRepository.findAll();
        List<UserInfoOperationDto> userInfoOperationDtos = new ArrayList<>();

        for (UserInfoOperation userInfoOperation : allUserInfoOperations){
            UserInfoOperationDto userInfoOperationDto = new UserInfoOperationDto();
            User user = userRepository.findById(userInfoOperation.getUserId()).orElseThrow(()-> new EntityNotFoundException("User not found"));

            userInfoOperationDto.setOperations(userInfoOperation.getOperations());
            userInfoOperationDto.setReputation((userInfoOperation.getReputation()));
            userInfoOperationDto.setName(user.getName());
            userInfoOperationDto.setSurname(user.getSurname());

            userInfoOperationDtos.add(userInfoOperationDto);
        }
        return userInfoOperationDtos;


    }
}
