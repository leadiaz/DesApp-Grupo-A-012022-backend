package ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model;

import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.dto.CryptoQuoteDto;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document
public class CryptoQuote {
    @Id
    private String id;
    private String crypto;
    private String usdPrice;
    private String pesosPrice;
    private LocalDateTime date;

    public CryptoQuote(){}
    public CryptoQuote(String crypto, String usdPrice, String pesosPrice) {
        this.crypto = crypto;
        this.usdPrice = usdPrice;
        this.pesosPrice = pesosPrice;
        this.date = LocalDateTime.now();
    }
    public CryptoQuoteDto toDto(){
        CryptoQuoteDto dto=new CryptoQuoteDto();
        dto.setCrypto(crypto);
        dto.setPesosPrice(pesosPrice);
        dto.setUsdPrice(usdPrice);
        return dto;
    }
}
