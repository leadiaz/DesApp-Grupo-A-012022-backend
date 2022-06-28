// deprecated code

//package ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model;
//
//import java.time.format.DateTimeFormatter;
//
//public class TransactionIntention {
//
//    public enum OperationEnum {
//        PURCHASE,
//        SALE
//    }
//
//    private CryptoAsset cryptoAsset;
//    private float amount;
//    private float price;
//    private float priceInArgentinianPesos;
//    private String userName;
//    private OperationEnum operation;
//
//    private String DateOfTransaction;
//    private String timeOfTransaction;
//
//    public TransactionIntention(CryptoAsset cryptoAsset, float amount, float price, float priceInArgentinianPesos, String userName, OperationEnum operation) {
//        this.cryptoAsset = cryptoAsset;
//        this.amount = amount;
//        this.price = price;
//        this.priceInArgentinianPesos = priceInArgentinianPesos;
//        this.userName = userName;
//        this.operation = operation;
//
//        this.DateOfTransaction = java.time.LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE);
//        this.timeOfTransaction = java.time.LocalTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE);
//    }
//}
//
