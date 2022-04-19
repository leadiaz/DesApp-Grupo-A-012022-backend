package ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.UserRegister;

public class User {
    private String name;
    private String surname;
    private String email;
    private String address;
    private String password;
    private String cvu;
    private String cryptoAssetAddress;

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCvu(String cvu) {
        this.cvu = cvu;
    }

    public void setCryptoAssetAddress(String cryptoAssetAddress) {
        this.cryptoAssetAddress = cryptoAssetAddress;
    }

    public boolean hasAllDataFilled(){
        return !(name.isEmpty() &&
               surname.isEmpty() &&
               email.isEmpty() &&
               address.isEmpty() &&
               password.isEmpty() &&
               cvu.isEmpty() &&
               cryptoAssetAddress.isEmpty());
    }
}
