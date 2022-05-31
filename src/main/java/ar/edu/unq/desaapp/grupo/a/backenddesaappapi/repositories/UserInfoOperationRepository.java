package ar.edu.unq.desaapp.grupo.a.backenddesaappapi.repositories;

import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.user.UserInfoOperation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserInfoOperationRepository extends MongoRepository<UserInfoOperation, String> {
}
