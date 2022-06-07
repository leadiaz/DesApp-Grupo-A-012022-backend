package ar.edu.unq.desaapp.grupo.a.backenddesaappapi.services;

import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.exception.EntityNotFoundException;
import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.dto.IntentionDto;
import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.user.User;
import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.dto.IntentionRequest;
import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.user.UserInfoOperation;
import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.user.dto.LoginUserDto;
import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.user.dto.UserDto;
import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.user.dto.UserRegisterDto;
import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.user.dto.UserTransactionIntentionDto;
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
    private IntentionService intentionService;
    private UserInfoOperationService userInfoOperationService;

    @Autowired
    UserService(UserRepository userRepository, IntentionService intentionService, UserInfoOperationService userInfoOperationService) {
        this.userRepository = userRepository;
        this.intentionService = intentionService;
        this.userInfoOperationService = userInfoOperationService;
    }
    @Transactional
    public UserRegisterDto save(UserRegisterDto userRegisterDto){
        User user = new User();
        user.fromDto(userRegisterDto);
        UserRegisterChecker.userRegisterValid(user);
        return this.userRepository.save(user).toDto();
    }

    @Transactional
    public List<UserRegisterDto> getAll() {
        return userRepository.findAll().stream().map(User::toDto).collect(Collectors.toList());
    }
    @Transactional
    public IntentionDto createIntention(String id, IntentionRequest intentionRequest) {
        User user = userRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("User Not found"));
        return intentionService.createIntention(user, intentionRequest);
    }
    @Transactional
    public UserTransactionIntentionDto getAllActiveIntentions(String id) {
        User user = userRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("User Not found"));
        UserInfoOperation userInfo = userInfoOperationService.findById(id);
        List<IntentionDto> intentionDtos = intentionService.findAllIntentionsActiveByUser(user);
        UserTransactionIntentionDto userTransactionIntentionDto = new UserTransactionIntentionDto();
        userTransactionIntentionDto.setIntentions(intentionDtos);
        userTransactionIntentionDto.setOperationAmount(userInfo.getOperations());
        if(userInfo.getOperations() == 0){
            userTransactionIntentionDto.setReputation("No operations");
        }else{
            userTransactionIntentionDto.setReputation(String.valueOf(userInfo.getPoints() / userInfo.getOperations()));
        }
        return userTransactionIntentionDto;
    }

    public UserDto login(LoginUserDto body) {
        User user = userRepository.findByEmailAndPassword(body.getEmail(), body.getPassword()).orElseThrow(() ->new EntityNotFoundException("Invalid Email or Password"));
        return user.toUserDto();
    }
}
