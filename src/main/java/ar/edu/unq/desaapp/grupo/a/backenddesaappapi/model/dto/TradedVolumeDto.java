package ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.dto;

import lombok.Data;


@Data
public class TradedVolumeDto {
    private String dateAndTime;
    private Float totalValueOperatedUSD;
    private Float totalValueOperatedArgentinianPesos;

    private String crypto;
    private Long totalNominalAmount;
    private Float currentValueUSD;
    private Float currentValueArgentinianPesos;
}
