package clases;

import clases.Reservation;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/***
 * @author Ronald
 */

public class User {

    private String dni;
    private String name;
    private String lastname;
    private Date birthDate;
    private int userCode;
    private List<Reservation> reservations;

    public User() {
    }

    public User(String dni, String name, String lastname, Date birthday) {
        this.dni = dni;
        this.name = name;
        this.lastname = lastname;
        this.birthDate = birthday;
        reservations = new ArrayList<>();
    }

    public User(String dni, String name, String lastname, Date birthday, int userCode) {
        this.dni = dni;
        this.name = name;
        this.lastname = lastname;
        this.birthDate = birthday;
        this.userCode = userCode;
        reservations = new ArrayList<>();
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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthday) {
        this.birthDate = birthday;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Dni: ").append(dni).append(System.lineSeparator());
        sb.append("Name: ").append(name).append(System.lineSeparator());
        sb.append("Lastname: ").append(lastname).append(System.lineSeparator());
        sb.append("Birthday: ").append(dateToString(birthDate)).append(System.lineSeparator());
        sb.append("UserCode: ").append(userCode).append(System.lineSeparator()).append(System.lineSeparator());
        if (reservations.size() > 0){
            sb.append("reservations: ").append(System.lineSeparator());
            for (Reservation reservation : reservations) {
                sb.append(reservation.toString());
            }
        }
        return sb.toString();
    }

    public String dateToString(Date date){
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return dateFormat.format(date);
    }
}