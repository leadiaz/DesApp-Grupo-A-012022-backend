package ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model;

import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.dto.IntentionDto;
import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.dto.IntentionRequest;
import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.user.User;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@Document("intention")
public class Intention {
    @Id
    private String id;
    private String crypto;
    private Long nominalAmount;
    private String cryptoPrice;
    private String operationAmountArg;
    private String operation;
    @DBRef
    private User user;
    @CreatedDate
    private LocalDateTime date;
    private Boolean active = true;
    public Intention(IntentionRequest intention, User user){
        this.crypto = intention.getCrypto();
        this.nominalAmount = intention.getNominalAmount();
        this.cryptoPrice = intention.getCryptoPrice();
        this.operationAmountArg = intention.getOperationAmountArg();
        this.operation = intention.getOperation();
        this.user = user;
    }
    public IntentionDto toDto(){
        IntentionDto dto = new IntentionDto();
        dto.setId(id);
        dto.setCrypto(crypto);
        dto.setNominalAmount(nominalAmount);
        dto.setCryptoPrice(cryptoPrice);
        dto.setOperationAmountArg(operationAmountArg);
        dto.setOperation(operation);
        dto.setUser(user.toUserDto());
        return dto;
    }
}
