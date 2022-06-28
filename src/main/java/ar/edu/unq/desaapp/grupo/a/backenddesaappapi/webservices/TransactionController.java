package ar.edu.unq.desaapp.grupo.a.backenddesaappapi.webservices;

import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.dto.request.TransactionRequest;
import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.services.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@RestController
@RequestMapping(value = "transaction")
public class TransactionController {
    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }
    @Operation(
            security = {@SecurityRequirement(name = "bearer")}
    )
    @PostMapping(value = "process", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity processTransaction(@RequestBody TransactionRequest body){
        return ResponseEntity.ok(transactionService.acceptIntetion(body));
    }

    @GetMapping(value="transaction/{from}/{to}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getTradedVolume(@PathVariable("from") @DateTimeFormat(pattern="yyyy-MM-dd") Date fromDate , @PathVariable("to") @DateTimeFormat(pattern="yyyy-MM-dd") Date toDate){


        return ResponseEntity.ok(transactionService.getTradedVolume(fromDate, toDate));
    }
}
