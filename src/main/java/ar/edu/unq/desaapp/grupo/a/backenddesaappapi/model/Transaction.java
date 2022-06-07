package ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model;

import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.dto.TransactionDto;
import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.user.User;
import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.user.UserInfoOperation;
import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.user.dto.UserDto;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document("transaction")
public class Transaction {
    @Id
    private String id;
    private String crypto;
    private Long nominalAmount;
    private String cryptoPrice;
    private String operationAmountArg;
    private String operation;
    private UserDto interestedUser;
    private UserDto publisherUser;
    private String cvu;
    private String cryptoWallet;
    @CreatedDate
    private LocalDateTime date;

    private String action;
    private Long operationQuantity;
    private String reputation;
    public Transaction(Intention intention, User user) {
        this.crypto = intention.getCrypto();
        this.nominalAmount = intention.getNominalAmount();
        this.cryptoPrice = intention.getCryptoPrice();
        this.operationAmountArg = intention.getOperationAmountArg();
        this.operation = intention.getOperation();
        this.interestedUser = user.toUserDto();
        this.publisherUser = intention.getUser().toUserDto();
        this.setAddress(user);

    }
    private void setAddress(User user){
        if(operation.equals("Buy")){
            this.cryptoWallet = user.getCryptoAssetAddress();
        }else{
            this.cvu = user.getCvu();
        }
    }

    public TransactionDto toDto() {
        return new TransactionDto();
    }
}
