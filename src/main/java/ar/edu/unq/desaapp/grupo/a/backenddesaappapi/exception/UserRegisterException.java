package ar.edu.unq.desaapp.grupo.a.backenddesaappapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserRegisterException extends RuntimeException {
    private static String DESCRIPTION = "Invalid Format User: ";
    public UserRegisterException(String msg){
        super(DESCRIPTION + msg);
    }
}
