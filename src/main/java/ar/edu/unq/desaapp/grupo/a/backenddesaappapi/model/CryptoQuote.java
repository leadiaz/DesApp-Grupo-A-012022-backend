package ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model;

import lombok.Data;

@Data
public class CryptoQuote {
    private String crypto;
    private String usdPrice;
    private String pesosPrice;

    public CryptoQuote(String crypto, String usdPrice, String pesosPrice) {
        this.crypto = crypto;
        this.usdPrice = usdPrice;
        this.pesosPrice = pesosPrice;
    }

    public String getPesosPrice() {
        return pesosPrice;
    }
}
