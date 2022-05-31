package ar.edu.unq.desaapp.grupo.a.backenddesaappapi.webservices;

import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.dto.IntentionRequest;
import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.user.dto.UserRegisterDto;
import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.user.dto.UserTransactionIntentionDto;
import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserRegisterDto> save(@RequestBody UserRegisterDto body){
        return ResponseEntity.ok(this.userService.save(body));
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserRegisterDto> getAll(){
        return this.userService.getAll();
    }

    @PostMapping(value = "/intention/{id}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createIntention(@PathVariable("id") String id, @RequestBody IntentionRequest intentionRequest){
        userService.createIntention(id, intentionRequest);
        return ResponseEntity.ok("created");
    }
    @GetMapping(value = "/intentions/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserTransactionIntentionDto> getAllActiveIntentions(@PathVariable("id") String id){
        return ResponseEntity.ok(userService.getAllActiveIntentions(id));
    }
}
