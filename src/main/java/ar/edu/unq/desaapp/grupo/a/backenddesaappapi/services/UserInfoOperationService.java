package ar.edu.unq.desaapp.grupo.a.backenddesaappapi.services;

import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.user.UserInfoOperation;
import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.repositories.UserInfoOperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoOperationService {
    private UserInfoOperationRepository userInfoOperationRepository;

    @Autowired
    public UserInfoOperationService(UserInfoOperationRepository userInfoOperationRepository) {
        this.userInfoOperationRepository = userInfoOperationRepository;
    }

    public UserInfoOperation findByUserId(String id) {
        return userInfoOperationRepository.findByUserId(id).orElse(new UserInfoOperation(id));
    }

    public UserInfoOperation save(UserInfoOperation userInfoOperation) {
        return this.userInfoOperationRepository.save(userInfoOperation);
    }
}
