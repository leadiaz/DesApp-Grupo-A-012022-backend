//package ar.edu.unq.desaapp.grupo.a.backenddesaappapi.webservices;
//
//import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.user.User;
//import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.user.dto.LoginUserDto;
//import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.user.dto.UserRegisterDto;
//import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.services.UserService;
//import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.util.ObjectMapperString;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//
//@WebMvcTest(UserController.class)
//public class UserControllerTest {
//    @Autowired
//    private MockMvc mvc;
//    @MockBean
//    private UserService userService;
//
//    @Test
//    public void testLoginUser() throws Exception{
//        LoginUserDto loginUserDto = new LoginUserDto();
//        loginUserDto.setEmail("lead@gmail.com");
//        loginUserDto.setPassword("Est03sUn4P4ss?");
//
//        User user = new User();
//        user.setId("01");
//        user.setName("Leandro");
//        user.setSurname("Diaz");
//        user.setEmail("lead@gmail.com");
//        user.setPassword("Est03sUn4P4ss?");
//        when(userService.login(loginUserDto)).thenReturn(user.toUserDto());
//        String requestJson = ObjectMapperString.getStringFromObject(loginUserDto);
//        mvc.perform(post("/user/login").contentType(MediaType.APPLICATION_JSON).content(requestJson))
//                .andExpect(jsonPath("$.name").value("Leandro"))
//                .andExpect(jsonPath("$.surname").value("Diaz"));
//    }
//
//    @Test
//    public void testRegisterUser() throws Exception {
//        UserRegisterDto userRegisterDto = new UserRegisterDto();
//        userRegisterDto.setName("Leandro 2");
//        userRegisterDto.setSurname("Diaz 2");
//        userRegisterDto.setEmail("leadiaz94@gmail.com");
//        userRegisterDto.setAddress("Calle falsa, 321");
//        userRegisterDto.setCvu("1111222233334444555500");
//        userRegisterDto.setPassword("Test123?");
//        userRegisterDto.setWallet("leantest");
//
//        User user = new User();
//        user.setName("Leandro 2");
//        user.setSurname("Diaz 2");
//        user.setEmail("leadiaz94@gmail.com");
//        user.setAddress("Calle falsa, 321");
//        user.setCvu("1111222233334444555500");
//        user.setPassword("Test123?");
//        user.setCryptoAssetAddress("leantest");
//
//        when(userService.save(userRegisterDto)).thenReturn(user.toDto());
//        String requestJson = ObjectMapperString.getStringFromObject(userRegisterDto);
//        mvc.perform(post("/user").contentType(MediaType.APPLICATION_JSON).content(requestJson))
//                .andExpect(jsonPath("$.wallet").value("leantest"))
//                .andExpect(jsonPath("$.cvu").value("1111222233334444555500"));
//
//    }
//}
