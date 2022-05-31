package ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model;

import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.user.dto.UserRegisterDto;
import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.user.User;
import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.util.UserRegisterChecker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserTest {
    private User user;
    private UserRegisterDto userRegisterDto;
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

        userRegisterDto = new UserRegisterDto();
        userRegisterDto.setName("Leandro 2");
        userRegisterDto.setSurname("Diaz 2");
        userRegisterDto.setEmail("leadiaz94@gmail.com");
        userRegisterDto.setAddress("Calle falsa, 321");
        userRegisterDto.setCvu("1111222233334444555500");
        userRegisterDto.setPassword("Test123?");
        userRegisterDto.setWallet("leantest");
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
    @Test
    void hasAllDataFilledTest(){
        Assertions.assertTrue(user.hasAllDataFilled());
    }
    @Test
    void toDtoTest(){
        UserRegisterDto dto = user.toDto();
        Assertions.assertEquals(dto.getName(), user.getName());
        Assertions.assertEquals(dto.getSurname(), user.getSurname());
        Assertions.assertEquals(dto.getEmail(), user.getEmail());
        Assertions.assertEquals(dto.getPassword(), user.getPassword());
        Assertions.assertEquals(dto.getAddress(), user.getAddress());
        Assertions.assertEquals(dto.getCvu(), user.getCvu());
        Assertions.assertEquals(dto.getWallet(), user.getCryptoAssetAddress());
    }
    @Test
    void fromDtoTest(){
        User user1 = new User();
        user1.fromDto(userRegisterDto);
        Assertions.assertEquals(user1.getName(), userRegisterDto.getName());
        Assertions.assertEquals(user1.getSurname(), userRegisterDto.getSurname());
        Assertions.assertEquals(user1.getEmail(), userRegisterDto.getEmail());
        Assertions.assertEquals(user1.getPassword(), userRegisterDto.getPassword());
        Assertions.assertEquals(user1.getAddress(), userRegisterDto.getAddress());
        Assertions.assertEquals(user1.getCvu(), userRegisterDto.getCvu());
        Assertions.assertEquals(user1.getCryptoAssetAddress(), userRegisterDto.getWallet());
    }
}
