package ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.operation;

import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.Intention;
import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.Transaction;
import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.user.UserInfoOperation;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OperationTypeBuy implements OperationType {
    private String operation;
    public OperationTypeBuy(){
        operation = "Buy";
    }
    @Override
    public boolean isBuy() {
        return true;
    }

    @Override
    public boolean isSale() {
        return false;
    }

    @Override
    public void processOperation(Transaction transaction, Intention intention, UserInfoOperation userInfoOperation, String action, BigDecimal variationPercent) {
        transaction.confirmTransactionBuy(intention, userInfoOperation, action, variationPercent);
    }
}
