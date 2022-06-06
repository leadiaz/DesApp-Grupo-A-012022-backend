package ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.Transaction;

import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.CryptoAsset;
import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.user.User;

import java.time.format.DateTimeFormatter;

public class TransactionIntention {


    private User userEmisor;
    private CryptoAsset cryptoAsset;
    private float nominalAmount;
    private float price;
    private float priceInArgentinianPesos;
  //  private String userName;
    private OperationEnum operation;

    public User getUserEmisor() {
        return userEmisor;
    }

    private String DateOfTransaction;
    private String timeOfTransaction;

    public TransactionIntention(CryptoAsset cryptoAsset, float amount, float price, float priceInArgentinianPesos, OperationEnum operation, User userEmisor) {
        this.cryptoAsset = cryptoAsset;
        this.nominalAmount = amount;
        this.price = price;
        this.priceInArgentinianPesos = priceInArgentinianPesos;
        this.userEmisor = userEmisor;
        this.operation = operation;
        this.userEmisor = userEmisor;

        this.DateOfTransaction = java.time.LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE);
        this.timeOfTransaction = java.time.LocalTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE);
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

    public float getPriceInArgentinianPesos() {
        return priceInArgentinianPesos;
    }

    public String getUserName() {
        return userName;
    }

    public OperationEnum getOperation() {
        return operation;
    }

    public String getDateOfTransaction() {
        return DateOfTransaction;
    }

    public String getTimeOfTransaction() {
        return timeOfTransaction;
    }
}

