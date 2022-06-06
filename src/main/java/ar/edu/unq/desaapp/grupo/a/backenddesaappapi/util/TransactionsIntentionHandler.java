package ar.edu.unq.desaapp.grupo.a.backenddesaappapi.util;

import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.CryptoAsset;
import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.Transaction.OperationEnum;
import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.Transaction.TransactionIntention;
import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.user.User;

import java.util.ArrayList;
import java.util.List;

public class TransactionsIntentionHandler {

    public static void createSaleTransactionIntention(CryptoAsset cryptoAsset, float amount, float price, float priceInArgentinianPesos, User userEmisor) {
        TransactionIntention transactionSaleIntention =
                new TransactionIntention(cryptoAsset, amount, price,priceInArgentinianPesos, OperationEnum.SALE, userEmisor);

        //todo persistance transactionSaleIntention
    }

    public static void createBuyTransactionIntention(CryptoAsset cryptoAsset, float amount, float price, float priceInArgentinianPesos, User userEmisor) {
        TransactionIntention transactionBuyIntention =
                new TransactionIntention(cryptoAsset, amount, price,priceInArgentinianPesos, OperationEnum.PURCHASE, userEmisor);

        //todo persistance transactionBuyIntention

    }

    public List<TransactionIntention> getActiveTransactionIntentionsOfUser(String userID, OperationEnum operation){
        List<TransactionIntention> AlltransactionIntentions = new ArrayList<TransactionIntention>();
        // todo retrieve data from persistance
        return AlltransactionIntentions;
    }
}
