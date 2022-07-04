package ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CryptoQuoteDto {
    private String crypto;
    private String usdPrice;
    private String pesosPrice;
    private LocalDateTime date;
}
