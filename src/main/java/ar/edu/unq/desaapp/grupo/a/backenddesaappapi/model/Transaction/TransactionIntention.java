package ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.Transaction;

import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.CryptoAsset;
import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.user.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TransactionIntention {


    private User userEmisor;
    private CryptoAsset cryptoAsset;
    private float nominalAmount;

    private float price;
    private float nominalValueInArgentinianPesos;
  //  private String userName;
    private OperationEnum operation;

    public User getUserEmisor() {
        return userEmisor;
    }

    private String dateOfTransactionText;
    private String timeOfTransactionText;
    private LocalDateTime timeOfIntentOftransaction;

    public TransactionIntention(CryptoAsset cryptoAsset, float amount, float price, float priceInArgentinianPesos, OperationEnum operation, User userEmisor) {
        this.cryptoAsset = cryptoAsset;
        this.nominalAmount = amount;
        this.price = price;
        this.nominalValueInArgentinianPesos = priceInArgentinianPesos;
        this.operation = operation;
        this.userEmisor = userEmisor;

        this.timeOfIntentOftransaction = LocalDateTime.now();
        this.dateOfTransactionText = java.time.LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE);
        this.timeOfTransactionText = timeOfIntentOftransaction.format(DateTimeFormatter.ISO_LOCAL_DATE);

    }

    public LocalDateTime getTimeOfIntentOftransaction() {
        return timeOfIntentOftransaction;
    }

    public CryptoAsset getCryptoAsset() {
        return cryptoAsset;
    }

    public float getNominalAmount() {
        return nominalAmount;
    }

    public float getPrice() {
        return price;
    }


    public OperationEnum getOperation() {
        return operation;
    }

    public String getDateOfTransactionText() {
        return dateOfTransactionText;
    }

    public String getTimeOfTransactionText() {
        return timeOfTransactionText;
    }



    public float getNominalValueInArgentinePesos() {
        return nominalValueInArgentinianPesos;
    }
}

