package ar.edu.unq.desaapp.grupo.a.backenddesaappapi.util;

import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.CryptoAsset;
import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.TransactionIntention;

public class TransactionsIntentionHandler {

    public static void createSaleTransactionIntention(CryptoAsset cryptoAsset, float amount, float price, float priceInArgentinianPesos, String userName, TransactionIntention){
        TransactionIntention transactionSaleIntention = new TransactionIntention
                        (cryptoAsset, amount, price,priceInArgentinianPesos,userName, TransactionIntention.OperationEnum.Venta);
        //todo persistance transactionSaleIntention

    }

    public static void createBuyTransactionIntention(CryptoAsset cryptoAsset, float amount, float price, float priceInArgentinianPesos, String userName, TransactionIntention){
        TransactionIntention transactionBuyIntention = new TransactionIntention
                (cryptoAsset, amount, price,priceInArgentinianPesos,userName, TransactionIntention.OperationEnum.Compra);
        //todo persistance transactionBuyIntention

    }
}
