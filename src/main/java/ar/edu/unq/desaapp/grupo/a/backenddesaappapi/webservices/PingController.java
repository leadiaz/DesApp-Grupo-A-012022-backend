package ar.edu.unq.desaapp.grupo.a.backenddesaappapi.webservices;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController()
@RequestMapping("ping")
public class PingController {
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> ping(){
        Map<String, String> map = new HashMap<>();
        map.put("status", "Running");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
