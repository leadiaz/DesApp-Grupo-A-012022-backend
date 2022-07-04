package ar.edu.unq.desaapp.grupo.a.backenddesaappapi.webservices;

import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.CryptoQuote;
import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.dto.CryptoQuoteDto;
import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.services.CryptoQuoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
    @Operation(
            security = {@SecurityRequirement(name = "bearer")}
    )
    @GetMapping(value = "{crypto}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getCryptoQuote(@PathVariable("crypto") String cryptoType){
        return ResponseEntity.ok(cryptoQuoteService.getCryptoQuote(cryptoType).toDto());
    }
    @Operation(
            security = {@SecurityRequirement(name = "bearer")}
    )
    @GetMapping(value = "all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CryptoQuoteDto> getAllCryptoQuote(){
        return cryptoQuoteService.getAllCryptoQuote();
    }
}
