package ar.edu.unq.desaapp.grupo.a.backenddesaappapi.exception;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "Error Message", description = "This message, throw by Exception")
public class ErrorMessage {

    private String exception;
    private String message;
    private String path;

    public ErrorMessage(Exception exception, String path){
        this.exception = exception.getClass().getSimpleName();
        this.message = exception.getMessage();
        this.path = path;
    }

    public String getException(){
        return exception;
    }

    public String getMessage(){
        return message;
    }

    public String getPath(){
        return path;
    }

    @Override
    public String toString() {
        return "ErrorMessage {" +
                "exception='" + exception +'\''+
                ", message='" + message + '\''+
                ", path='" + path + '\''+
                '}';
    }
}
