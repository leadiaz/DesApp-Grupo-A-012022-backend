package ar.edu.unq.desaapp.grupo.a.backenddesaappapi.webservices;

import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.dto.IntentionDto;
import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.dto.IntentionRequest;
import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.dto.jwt.JwtResponseDto;
import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.user.UserInfoOperation;
import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.user.dto.LoginUserDto;
import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.user.dto.UserInfoOperationDto;
import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.user.dto.UserRegisterDto;
import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.user.dto.UserTransactionIntentionDto;
import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.services.UserInfoOperationService;
import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.services.UserService;
import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.services.jwt.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {
    private UserService userService;
    private AuthService authService;
    private UserInfoOperationService userInfoOperationService;

    @Autowired
    public UserController(UserService userService, AuthService authService,UserInfoOperationService userInfoOperationService) {
        this.userService = userService;
        this.authService = authService;
        this.userInfoOperationService = userInfoOperationService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserRegisterDto> save(@RequestBody UserRegisterDto body){
        return ResponseEntity.ok(this.userService.save(body));
    }
    @Operation(
            security = {@SecurityRequirement(name = "bearer")}
    )
    @GetMapping(value= "all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserRegisterDto> getAll(){
        return this.userService.getAll();
    }

    @PostMapping(value = "login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JwtResponseDto> login(@RequestBody LoginUserDto authenticationRequest) throws Exception {
        String token = authService.createAuthenticationToken(authenticationRequest);
        return ResponseEntity.ok(new JwtResponseDto(token));

    }
    @Operation(
            security = {@SecurityRequirement(name = "bearer")}
    )
    @PostMapping(value = "intention/buy",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IntentionDto> createIntentionBuy(@RequestBody IntentionRequest intentionRequest){
        return ResponseEntity.ok(userService.createIntentionBuy(intentionRequest));
    }
    @Operation(
            security = {@SecurityRequirement(name = "bearer")}
    )
    @PostMapping(value = "intention/sale",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IntentionDto> createIntentionSale(@RequestBody IntentionRequest intentionRequest){
        return ResponseEntity.ok(userService.createIntentionSale(intentionRequest));
    }
    @Operation(
            security = {@SecurityRequirement(name = "bearer")}
    )
    @GetMapping(value = "intentions/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserTransactionIntentionDto> getAllActiveIntentions(@PathVariable("id") String id){
        return ResponseEntity.ok(userService.getAllActiveIntentions(id));
    }

    @Operation(
            security = {@SecurityRequirement(name = "bearer")}
    )
    @GetMapping(value= "allUserInfoOperation", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserInfoOperationDto> getAllUserInfoOperation(){
        return this.userInfoOperationService.getAllUserInfoOperation();
    }
}
