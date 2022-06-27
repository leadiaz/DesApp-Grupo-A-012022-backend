package ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.dto.jwt;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(name = "Response JWT", description = "This is jwt token for use apis in Deals")
public class JwtResponseDto  implements Serializable {
    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwttoken;

    public JwtResponseDto(String jwttoken){
        this.jwttoken = jwttoken;
    }
}
