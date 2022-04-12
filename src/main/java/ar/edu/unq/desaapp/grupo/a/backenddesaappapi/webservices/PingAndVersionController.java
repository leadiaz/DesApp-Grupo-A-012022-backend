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
public class PingAndVersionController {
    @GetMapping(value = "ping", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> ping(){
        Map<String, String> map = new HashMap<>();
        map.put("status", "Running");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
    @GetMapping(value = "version", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> version(){
        Map<String, String> map = new HashMap<>();
        map.put("version", "0.0.1");
        return ResponseEntity.ok(map);
    }
}
