package ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model;

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
}
