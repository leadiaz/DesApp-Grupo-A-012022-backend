package ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.Transaction;

import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.CryptoAsset;
import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.Transaction.OperationEnum;
import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.user.User;

public class ProcessTransactionIntention {

    private int reputationPointsDecreaseByCancelation = 20;
    private CryptoAsset cryptoAsset;
    private float nominalAmount;

    private float cryptoAssetValue;
    private float price;
    private String userNameAndSurname;
    private int totalTransactionsMadeByUser;
    private int userReputation;
    private String deliveryAddress;
    private String cancelMessage = "Cancelar";
    private String confirmMessage;

    private String sellConfirmMessage = "Confirmar recepción";
    private String buyConfirmMessage = "Realice la transferencia";

    private User userSelling;
    private User userBuying;

    public ProcessTransactionIntention(TransactionIntention transactionIntention, User user) {
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

    public void onTransactionCanceledByUser(TransactionIntention transactionIntention, User userThatCancelledTransaction) {
            userThatCancelledTransaction.decreaseReputation(reputationPointsDecreaseByCancelation);
        }
    }

    public void onTransactionAccepted(TransactionIntention transactionIntention) {

    }
}
