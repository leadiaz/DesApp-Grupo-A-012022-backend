package ar.edu.unq.desaapp.grupo.a.backenddesaappapi.webservices;

import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.user.User;
import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.user.dto.LoginUserDto;
import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(UserController.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private UserService userService;

    @Test
    public void testLoginUser() throws Exception{
        LoginUserDto loginUserDto = new LoginUserDto();
        loginUserDto.setEmail("lead@gmail.com");
        loginUserDto.setPassword("Est03sUn4P4ss?");

        User user = new User();
        user.setId("01");
        user.setName("Leandro");
        user.setSurname("Diaz");
        user.setEmail("lead@gmail.com");
        user.setPassword("Est03sUn4P4ss?");
        when(userService.login(loginUserDto)).thenReturn(user.toUserDto());
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(loginUserDto);
        mvc.perform(post("/user/login").contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(jsonPath("$.name").value("Leandro"))
                .andExpect(jsonPath("$.surname").value("Diaz"));
    }
}
