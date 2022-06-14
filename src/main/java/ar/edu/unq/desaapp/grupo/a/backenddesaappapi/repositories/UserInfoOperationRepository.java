package ar.edu.unq.desaapp.grupo.a.backenddesaappapi.repositories;

import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.user.UserInfoOperation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserInfoOperationRepository extends MongoRepository<UserInfoOperation, String> {
    Optional<UserInfoOperation> findByUserId(String id);
}
