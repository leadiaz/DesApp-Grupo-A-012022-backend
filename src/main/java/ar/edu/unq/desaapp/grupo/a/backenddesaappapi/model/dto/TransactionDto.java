package ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.dto;

import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.Transaction;
import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.user.dto.UserDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TransactionDto {
    private String crypto;
    private Long nominalAmount;
    private String cryptoPrice;
    private String operationAmountArg;
    private String operation;
    private UserDto interestedUser;
    private UserDto publisherUser;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String cvu;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String cryptoWallet;
    private LocalDateTime date;
    private String action;
    private Long operationQuantity;
    private String reputation;

    public TransactionDto(){}
    public TransactionDto(Transaction transaction){
        crypto = transaction.getCrypto();
        nominalAmount = transaction.getNominalAmount();
        cryptoPrice = transaction.getCryptoPrice();
        operationAmountArg = transaction.getOperationAmountArg();
        operation = transaction.getOperation();
        interestedUser = transaction.getInterestedUser();
        publisherUser = transaction.getPublisherUser();
        cvu = transaction.getCvu();
        cryptoWallet = transaction.getCryptoWallet();
        date = transaction.getDate();
        action = transaction.getAction();
        operationQuantity = transaction.getOperationQuantity();
        reputation = transaction.getReputation();

    }
}
