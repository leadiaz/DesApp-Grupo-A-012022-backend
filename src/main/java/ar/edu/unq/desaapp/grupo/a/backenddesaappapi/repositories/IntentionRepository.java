package ar.edu.unq.desaapp.grupo.a.backenddesaappapi.repositories;


import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.Intention;
import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface IntentionRepository extends MongoRepository<Intention, String> {
    List<Intention> findAllByUserAndActive(User user, boolean b);
}
