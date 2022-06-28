package ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.dto;

import lombok.Data;


@Data
public class TradedVolumeDto {
    private String dateAndTime;
    private Long totalValueOperatedUSD;
    private Long totalValueOperatedArgentinianPesos;

    private String crypto;
    private Long totalNominalAmount;
    private Long currentValueUSD;
    private Long currentValueArgentinianPesos;
}
