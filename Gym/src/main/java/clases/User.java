package clases;

import java.util.List;

/***
 * @author Ronald
 */

public class User {

    private String dni;
    private String name;
    private String lastname;
    private int age;
    private int userCode;
    private List<Reservation> reservations;


    public User(){};

    public User(String dni, String name, String lastname, int age, int userCode) {
        this.dni = dni;
        this.name = name;
        this.lastname = lastname;
        this.age = age;
        this.userCode = userCode;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getUserCode() {
        return userCode;
    }

    public void setUserCode(int userCode) {
        this.userCode = userCode;
    }
}
