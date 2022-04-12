package ar.edu.unq.desaapp.grupo.a.backenddesaappapi.webservices;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController()
public class PingAndVersionController {
    @GetMapping(value = "ping", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> ping(){
        Map<String, String> map = new HashMap<>();
        map.put("status", "Running");
        return ResponseEntity.ok(map);
    }
    @GetMapping(value = "version", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> version(){
        Map<String, String> map = new HashMap<>();
        map.put("version", "0.0.1");
        return ResponseEntity.ok(map);
    }
}
