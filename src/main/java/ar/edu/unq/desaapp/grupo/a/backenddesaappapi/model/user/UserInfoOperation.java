package ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.user;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("userInfoOperation")
public class UserInfoOperation {
    @Id
    private String id;
    private String userId;
    private Long points;
    private Long operations;

    public UserInfoOperation(String userId){
        this.userId = userId;
        this.points = 0L;
        this.operations = 0L;
    }
}
