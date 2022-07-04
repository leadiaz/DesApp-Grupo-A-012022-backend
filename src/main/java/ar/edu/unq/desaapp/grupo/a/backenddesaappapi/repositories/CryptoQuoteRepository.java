package ar.edu.unq.desaapp.grupo.a.backenddesaappapi.repositories;

import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.CryptoQuote;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CryptoQuoteRepository extends MongoRepository<CryptoQuote, String> {
    List<CryptoQuote> findByCrypto(String cryptoType);
}
