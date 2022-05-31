package ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.user;

import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.user.dto.UserRegisterDto;
import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.user.dto.UserDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("user")
public class User {
    @Id
    private String id;
    private String name;
    private String surname;
    @Indexed(unique = true)
    private String email;
    private String address;
    private String password;
    private String cvu;
    @JsonProperty("wallet")
    private String cryptoAssetAddress;

    public boolean hasAllDataFilled(){
        return !(name.isEmpty() &&
               surname.isEmpty() &&
               email.isEmpty() &&
               address.isEmpty() &&
               password.isEmpty() &&
               cvu.isEmpty() &&
               cryptoAssetAddress.isEmpty());
    }

    public void fromDto(UserRegisterDto userRegisterDto) {
        id= userRegisterDto.getId();
        name= userRegisterDto.getName();
        surname= userRegisterDto.getSurname();
        email= userRegisterDto.getEmail();
        address= userRegisterDto.getAddress();
        password= userRegisterDto.getPassword();
        cvu= userRegisterDto.getCvu();
        cryptoAssetAddress= userRegisterDto.getWallet();
    }
    public UserRegisterDto toDto(){
        UserRegisterDto dto = new UserRegisterDto();
        dto.setId(id);
        dto.setName(name);
        dto.setSurname(surname);
        dto.setEmail(email);
        dto.setAddress(address);
        dto.setPassword(password);
        dto.setCvu(cvu);
        dto.setWallet(cryptoAssetAddress);
        return dto;
    }
    public UserDto toUserDto(){
        UserDto dto = new UserDto();
        dto.setName(name);
        dto.setSurname(surname);
        return dto;
    }
}
