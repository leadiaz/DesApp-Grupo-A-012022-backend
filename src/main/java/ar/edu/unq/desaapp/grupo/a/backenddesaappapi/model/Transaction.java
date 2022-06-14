package ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model;

import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.dto.TransactionDto;
import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.user.User;
import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.user.UserInfoOperation;
import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.user.dto.UserDto;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.Duration;
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
        this.operation = intention.getOperation().getOperation();
        this.interestedUser = user.toUserDto();
        this.publisherUser = intention.getUser().toUserDto();
        this.date = LocalDateTime.now();
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
        return new TransactionDto(this);
    }

    public void confirmTransactionBuy(Intention intention, UserInfoOperation userInfoOperation, String action, BigDecimal variationPercent) {
        if(variationPercent.compareTo(BigDecimal.valueOf(5)) > 0){
            this.cancelBySystem(userInfoOperation);
        }else{
            evalAction(userInfoOperation, action, intention.getDate(), this.getDate());
            this.action = action;
            this.setOperationQuantity(userInfoOperation.getOperations());
            this.setReputation(userInfoOperation.getReputation());
        }
    }
    private void evalAction(UserInfoOperation userInfoOperation, String action, LocalDateTime intentionDate, LocalDateTime transactionDate) {
        if(!action.equals("CANCEL")){
            userInfoOperation.confirmOperation();
            increaseReputation(userInfoOperation, intentionDate, transactionDate);
        }else {
            decreaseReputation(userInfoOperation);
        }
    }

    private void decreaseReputation(UserInfoOperation userInfoOperation) {
        userInfoOperation.subsPoints(20L);
    }

    private void increaseReputation(UserInfoOperation userInfoOperation, LocalDateTime intentionDate, LocalDateTime transactionDate){
        Duration duration = Duration.between(intentionDate, transactionDate);
        long timeInMinutes = duration.getSeconds()/60;
        if(timeInMinutes <= 30){
            userInfoOperation.addPoints(10L);
        }else{
            userInfoOperation.addPoints(5L);
        }
    }
    public void confirmTransactionSale(Intention intention, UserInfoOperation userInfoOperation, String action, BigDecimal variationPercent){
        if(variationPercent.compareTo(BigDecimal.valueOf(-5)) < 0){
            this.cancelBySystem(userInfoOperation);
        }else{
            evalAction(userInfoOperation, action, intention.getDate(), this.getDate());
            this.action = action;
            this.setOperationQuantity(userInfoOperation.getOperations());
            this.setReputation(userInfoOperation.getReputation());
        }
    }
    public void cancelBySystem(UserInfoOperation userInfoOperation){
        this.action = "CANCEL BY SYSTEM";
        this.operationQuantity = userInfoOperation.getOperations();
        this.reputation = userInfoOperation.getReputation();
    }
}
