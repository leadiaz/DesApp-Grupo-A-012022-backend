package ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.dto;

import lombok.Data;

@Data
public class CryptoQuoteDto {
    private String crypto;
    private String usdPrice;
    private String pesosPrice;
}
