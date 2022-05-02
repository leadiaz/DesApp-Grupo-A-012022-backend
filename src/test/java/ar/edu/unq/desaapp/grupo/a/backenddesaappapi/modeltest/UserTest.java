package ar.edu.unq.desaapp.grupo.a.backenddesaappapi.modeltest;

import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.User;
import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.util.UserRegisterChecker;
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
    void minAndMaxLengthNameTest(){
        Assertions.assertTrue(UserRegisterChecker.hasMinMaxCharacterNumber(3, 30, user.getName()));
    }
    @Test
    void minAndMaxLengthSurnameTest(){
        Assertions.assertTrue(UserRegisterChecker.hasMinMaxCharacterNumber(3, 30, user.getName()));
    }
    @Test
    void correctEmailFormatTest(){
        Assertions.assertTrue(UserRegisterChecker.hasEmailFormat(user.getEmail()));
    }
    @Test
    void minAndMaxLengthAddressTest(){
        Assertions.assertTrue(UserRegisterChecker.hasMinMaxCharacterNumber(10, 30, user.getAddress()));
    }

    @Test
    //at least 1 lowercase, 1 uppercase, 1 special character and min 6
    void validPasswordTest(){
        Assertions.assertTrue(UserRegisterChecker.isValidPassword(user.getPassword()));
    }
    @Test
    void validCVUTest(){
        Assertions.assertTrue(UserRegisterChecker.hasMinMaxCharacterNumber(22, 22, user.getCvu()));
    }
    @Test
    void validCriptoAssetAddressTest(){
        Assertions.assertTrue(UserRegisterChecker.hasMinMaxCharacterNumber(8,8, user.getCryptoAssetAddress()));
    }

}
