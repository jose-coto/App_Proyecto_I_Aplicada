package domain;

/**
 * Created by Machito on 31/8/2017.
 */

public class User {

    private int identificacionNumber;
    private String name;
    private String email;
    private String password;
    private String address;
    private String phoneNumber;

    public User() {
    }

    public User(int identificacionNumber, String name, String email, String password, String address, String phoneNumber) {
        this.identificacionNumber = identificacionNumber;
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public int getIdentificacionNumber() {
        return identificacionNumber;
    }

    public void setIdentificacionNumber(int identificacionNumber) {
        this.identificacionNumber = identificacionNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
