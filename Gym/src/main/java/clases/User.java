package clases;

import java.util.Date;
import java.util.List;

/***
 * @author Ronald
 */

public class User {

    private String dni;
    private String name;
    private String lastname;
    private Date birthday;
    private int userCode;
    private List<Reservation> reservations;

    public User() {
    }

    public User(String dni, String name, String lastname, Date birthday, int userCode) {
        this.dni = dni;
        this.name = name;
        this.lastname = lastname;
        this.birthday = birthday;
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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getUserCode() {
        return userCode;
    }

    public void setUserCode(int userCode) {
        this.userCode = userCode;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
}
