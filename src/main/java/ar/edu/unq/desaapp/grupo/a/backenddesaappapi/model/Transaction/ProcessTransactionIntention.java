package ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.Transaction;

import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.CryptoAsset;
import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.Transaction.OperationEnum;
import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.user.User;
import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.services.CryptoQuoteService;
import org.apache.tomcat.jni.Local;

import java.time.Duration;
import java.time.LocalDateTime;

public class ProcessTransactionIntention {

    private int reputationPointsDecreaseByCancelation = 20;
    private CryptoAsset cryptoAsset;
    private float nominalAmount;

    private float cryptoAssetValue;
    private float price;
    private float minutesBeforeReputationBonusLost = 30.0f;
    private String userNameAndSurname;
    private int totalTransactionsMadeByUser;
    private int userReputation;
    private String deliveryAddress;
    private String cancelMessage = "Cancelar";
    private String confirmMessage;

    private String sellConfirmMessage = "Confirmar recepción";
    private String buyConfirmMessage = "Realice la transferencia";

    private CryptoQuoteService cryptoQuoteService = new CryptoQuoteService();
    private User userSelling;
    private User userBuying;

    public ProcessTransactionIntention(TransactionIntention transactionIntention, User user) {
        if (!isTransactionIntentionPossible(transactionIntention)) {
            cancelTransaction(transactionIntention,user);
            return;
        }
        this.cryptoAsset = transactionIntention.getCryptoAsset();
        this.nominalAmount = transactionIntention.getNominalAmount();

        this.cryptoAssetValue = transactionIntention.getCryptoAsset().getValue();
        this.userNameAndSurname = user.getName() + " " + user.getSurname();
        this.totalTransactionsMadeByUser = user.getTotalTransactionsCompleted();
        this.userReputation = user.getReputation();

        OperationEnum operation = transactionIntention.getOperation();

        switch (operation){
            case PURCHASE: {
                this.confirmMessage = buyConfirmMessage;
                this.userSelling = transactionIntention.getUserEmisor();
                this.userBuying = user;
                break;
            }
            case SALE: {
                this.confirmMessage = sellConfirmMessage;
                this.userBuying = transactionIntention.getUserEmisor();
                this.userSelling = user;
                break;
            }
        }
    }

    private void cancelTransaction(TransactionIntention transactionIntention, User user2) {
        // hace falta hacer algo aca?
    }



    private boolean isTransactionIntentionPossible(TransactionIntention transactionIntention) {
        boolean result = true;

        float nominalValue = transactionIntention.getNominalValueInArgentinePesos();
        float currentValue = Float.parseFloat(cryptoQuoteService.getCryptoQuote(transactionIntention.getCryptoAsset().getName()).getPesosPrice()); //de donde se sacaban

        OperationEnum operation = transactionIntention.getOperation();

        switch (operation){
            case PURCHASE: {
                result = nominalValue > currentValue;
                break;
            }
            case SALE: {
                result = nominalValue < currentValue;
                break;
            }
        }
        return result;
    }

    public void onTransactionCanceledByUser(TransactionIntention transactionIntention, User userThatCancelledTransaction) {
        userThatCancelledTransaction.decreaseReputation(reputationPointsDecreaseByCancelation);
    }


    public void onTransactionAccepted(TransactionIntention transactionIntention) {

        boolean operationHasBeenCompletedInPositiveTime = isOperationCompletedOnPositiveTime(transactionIntention);
    }

    private boolean isOperationCompletedOnPositiveTime(TransactionIntention transactionIntention) {
        LocalDateTime timeOfTransaction = transactionIntention.getTimeOfIntentOftransaction();
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(timeOfTransaction, now);

        return duration.toMinutes() < minutesBeforeReputationBonusLost;
    }
}
