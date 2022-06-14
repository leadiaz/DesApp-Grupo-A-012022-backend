package ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.dto;

import lombok.Data;

@Data
public class IntentionRequest {
    private String userEmail;
    private String crypto;
    private Long nominalAmount;
    private String cryptoPrice;
    private String operationAmountArg;
}
