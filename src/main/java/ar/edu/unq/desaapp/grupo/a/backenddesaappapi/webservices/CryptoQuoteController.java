package ar.edu.unq.desaapp.grupo.a.backenddesaappapi.webservices;

import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.CryptoQuote;
import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.services.CryptoQuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "quote")
public class CryptoQuoteController {
    private final CryptoQuoteService cryptoQuoteService;
    @Autowired
    public CryptoQuoteController(CryptoQuoteService cryptoQuoteService) {
        this.cryptoQuoteService = cryptoQuoteService;
    }
    @GetMapping(value = "{crypto}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getCrytoQuote(@PathVariable("crypto") String cryptoType){
        return ResponseEntity.ok(cryptoQuoteService.getCrytoQuote(cryptoType));
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CryptoQuote> getAllCryptoQuote(){
        return cryptoQuoteService.getAllCryptoQuote();
    }
}
