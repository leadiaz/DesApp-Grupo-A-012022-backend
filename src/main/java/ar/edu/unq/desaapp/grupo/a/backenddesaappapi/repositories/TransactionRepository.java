package ar.edu.unq.desaapp.grupo.a.backenddesaappapi.repositories;

import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransactionRepository extends MongoRepository<Transaction, String> {
}
