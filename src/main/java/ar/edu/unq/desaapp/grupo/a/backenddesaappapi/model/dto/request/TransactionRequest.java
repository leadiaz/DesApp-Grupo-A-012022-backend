package ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.dto.request;

import lombok.Data;

@Data
public class TransactionRequest {
    private String userEmail;
    private String idIntention;
}
