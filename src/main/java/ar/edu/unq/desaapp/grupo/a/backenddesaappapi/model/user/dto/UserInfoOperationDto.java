package ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.user.dto;

import lombok.Data;

@Data
public class UserInfoOperationDto {
    private String name;
    private String surname;
    private Long reputation;
    private Long operations;
}
