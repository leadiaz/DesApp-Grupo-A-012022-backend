package ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class USDResponse {
    @JsonProperty("fecha")
    private String date;
    @JsonProperty("compra")
    private String purchase;
    @JsonProperty("venta")
    private String sale;
}
