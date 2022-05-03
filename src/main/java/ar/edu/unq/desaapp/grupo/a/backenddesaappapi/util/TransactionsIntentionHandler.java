package ar.edu.unq.desaapp.grupo.a.backenddesaappapi.util;

import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.CryptoAsset;
import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.TransactionIntention;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TransactionsIntentionHandler {

    public static void createSaleTransactionIntention(CryptoAsset cryptoAsset, float amount, float price, float priceInArgentinianPesos, String userName) {
        TransactionIntention transactionSaleIntention =
                new TransactionIntention(cryptoAsset, amount, price,priceInArgentinianPesos,userName, TransactionIntention.OperationEnum.Venta);

        //todo persistance transactionSaleIntention
    }

    public static void createBuyTransactionIntention(CryptoAsset cryptoAsset, float amount, float price, float priceInArgentinianPesos, String userName) {
        TransactionIntention transactionBuyIntention =
                new TransactionIntention(cryptoAsset, amount, price,priceInArgentinianPesos,userName, TransactionIntention.OperationEnum.Compra);

        //todo persistance transactionBuyIntention

    }

    public List<TransactionIntention> getActiveTransactionIntentionsOfUser(String userID, TransactionIntention.OperationEnum operation){
        List<TransactionIntention> AlltransactionIntentions = new ArrayList<TransactionIntention>();
        // todo retrieve data from persistance
        return AlltransactionIntentions;
    }
}
