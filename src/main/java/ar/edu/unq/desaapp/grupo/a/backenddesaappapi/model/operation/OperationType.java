package ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.operation;

import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.Intention;
import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.Transaction;
import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.user.UserInfoOperation;

import java.math.BigDecimal;

public interface OperationType {
    boolean isBuy();
    boolean isSale();
    String getOperation();

    void processOperation(Transaction transaction, Intention intention, UserInfoOperation userInfoOperation, String action, BigDecimal variationPercent);
}
