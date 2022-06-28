package ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TradedVolumeDto {
    private LocalDateTime dateAndTime;
    private Long totalValueOperatedUSD;
    private Long totalValueOperatedArgentinianPesos;

    private String crypto;
    private Long totalNominalAmount;
    private Long currentValueUSD;
    private Long currentValueArgentinianPesos;
}
