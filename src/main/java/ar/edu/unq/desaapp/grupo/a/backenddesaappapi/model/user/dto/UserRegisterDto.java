package ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.user.dto;

import lombok.Data;

@Data
public class UserRegisterDto {
    private String id;
    private String name;
    private String surname;
    private String email;
    private String address;
    private String password;
    private String cvu;
    private String wallet;
}
