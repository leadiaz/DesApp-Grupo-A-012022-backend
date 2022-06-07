package ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BinanceResponce {
    @JsonProperty("symbol")
    private String crypto;
    private String price;

    public BinanceResponce(String symbol, String price){
        crypto = symbol;
        this.price = price;
    }
}
