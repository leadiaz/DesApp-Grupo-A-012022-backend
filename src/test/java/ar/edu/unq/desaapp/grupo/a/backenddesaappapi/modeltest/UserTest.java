package ar.edu.unq.desaapp.grupo.a.backenddesaappapi.modeltest;

import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserTest {
    private User user;
    @BeforeEach
    public void setUp(){
        user = new User();
        user.setName("Leandro");
        user.setSurname("Diaz");
        user.setEmail("diazleandro4@gmail.com");
        user.setAddress("Calle falsa, 123");
        user.setCvu("0011112222333344445555");
        user.setPassword("Test123?");
        user.setCryptoAssetAddress("leantest");
    }

    @Test
    public void getNameTest(){
        Assertions.assertEquals(user.getName(), "Leandro");
    }
    @Test
    void minLengthNameTest(){
        Assertions.assertTrue(user.getName().length() >= 3);
    }
    @Test
    void maxLengthNameTest(){
        Assertions.assertTrue(user.getName().length() <= 30);
    }
}
