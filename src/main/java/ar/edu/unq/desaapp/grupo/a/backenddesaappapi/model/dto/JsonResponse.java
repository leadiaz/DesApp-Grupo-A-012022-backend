package ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.dto;

import lombok.Data;

@Data
public class JsonResponse {
    private String message;

    public JsonResponse(String s) {
        message = s;
    }
}
