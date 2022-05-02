package ar.edu.unq.desaapp.grupo.a.backenddesaappapi.exception;

public class UserRegisterException extends RuntimeException {
    private static String DESCRIPTION = "Invalid Format User: ";
    public UserRegisterException(String msg){
        super(DESCRIPTION + msg);
    }
}
