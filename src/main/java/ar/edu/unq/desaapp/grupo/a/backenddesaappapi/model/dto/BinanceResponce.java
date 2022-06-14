package ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BinanceResponce {
    @JsonProperty("symbol")
    private String symbol;
    private String price;

//    public BinanceResponce(String symbol, String price){
//        this.symbol = symbol;
//        this.price = price;
//    }
}
