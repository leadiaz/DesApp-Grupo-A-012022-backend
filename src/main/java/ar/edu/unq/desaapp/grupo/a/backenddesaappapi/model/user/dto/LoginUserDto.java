package ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.user.dto;

import lombok.Data;

@Data
public class LoginUserDto {
    private String email;
    private String password;
}
