package ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.operation;

import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.Intention;
import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.Transaction;
import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.user.UserInfoOperation;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OperationTypeSale implements OperationType {
    private String operation;
    public OperationTypeSale(){
        operation = "Sale";
    }
    @Override
    public boolean isBuy() {
        return false;
    }

    @Override
    public boolean isSale() {
        return true;
    }

    @Override
    public void processOperation(Transaction transaction, Intention intention, UserInfoOperation userInfoOperation, String action, BigDecimal variationPercent) {
        transaction.confirmTransactionSale(intention, userInfoOperation, action, variationPercent);
    }
}
