package ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.dto;

import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.user.dto.UserDto;
import lombok.Data;

@Data
public class IntentionDto {
    private String crypto;
    private Long nominalAmount;
    private String cryptoPrice;
    private String operationAmountArg;
    private String operation;
    private UserDto user;
    private String date;
}
