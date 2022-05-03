package ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model;

public class TransactionIntention {

    public enum OperationEnum {
        Compra,
        Venta
    }

    private CryptoAsset cryptoAsset;
    private float amount;
    private float price;
    private float priceInArgentinianPesos;
    private String userName;
    private OperationEnum operation;

    public TransactionIntention(CryptoAsset cryptoAsset, float amount, float price, float priceInArgentinianPesos, String userName, OperationEnum operation) {
        this.cryptoAsset = cryptoAsset;
        this.amount = amount;
        this.price = price;
        this.priceInArgentinianPesos = priceInArgentinianPesos;
        this.userName = userName;
        this.operation = operation;
    }
}

