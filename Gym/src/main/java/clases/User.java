package clases;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static utilities.Converter.dateToString;

/**
 * The User class represents a user object.
 * @author Shah
 */

public class User {

    private String dni;
    private String name;
    private String lastname;
    private Date birthDate;
    private int userCode;
    private List<Reservation> reservations = new ArrayList<>();

    /**
     * Constructs an empty user
     */
    public User() {
    }

    /**
     * Constructs a user object assigning the attributes that are received by parameter
     *
     * @param dni dni
     * @param name name
     * @param lastname lastname
     * @param birthDate birthDate
     */
    public User(String dni, String name, String lastname, Date birthDate) {
        this.dni = dni;
        this.name = name;
        this.lastname = lastname;
        this.birthDate = birthDate;
    }

    /**
     * Constructs a user object assigning the attributes that are received by parameter
     *
     * @param dni dni
     * @param name name
     * @param lastname lastname
     * @param birthDate birthDate
     * @param userCode user code
     */
    public User(String dni, String name, String lastname, Date birthDate, int userCode) {
        this.dni = dni;
        this.name = name;
        this.lastname = lastname;
        this.birthDate = birthDate;
        this.userCode = userCode;
    }

    /**
     * Return Dni
     * @return return dni
     */
    public String getDni() {
        return dni;
    }

    /**
     * Set dni
     * @param dni
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * Return name
     * @return return name
     */
    public String getName() {
        return name;
    }

    /**
     * Set name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Return last name
     * @return last name
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * Set last name
     * @param lastname
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * Return birthday
     * @return birthday
     */
    public Date getBirthDate() {
        return birthDate;
    }

    /**
     * Set birthday
     * @param birthday
     */
    public void setBirthDate(Date birthday) {
        this.birthDate = birthday;
    }

    /**
     * Return user code
     * @return user code
     */
    public int getUserCode() {
        return userCode;
    }

    /**
     * Set user code
     * @param userCode
     */
    public void setUserCode(int userCode) {
        this.userCode = userCode;
    }

    /**
     * Return user {@link clases.Reservation} list
     * @return {@link clases.Reservation} list
     */
    public List<Reservation> getReservations() {
        return reservations;
    }

    /**
     * Set {@link clases.Reservation} list
     * @param reservations
     */
    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userCode == user.userCode && dni.equals(user.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni, userCode);
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
}