package ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.user.dto;

import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.dto.IntentionDto;
import lombok.Data;

import java.util.List;

@Data
public class UserTransactionIntentionDto {
    private Long operationAmount;
    private String reputation;
    private List<IntentionDto> intentions;

}
