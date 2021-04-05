package clases;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/***
 * Class to handle reservations
 * @author Ronald
 */

public class Reservation {
    private int reservationCode;
    private Date date;
    private int userCode;
    private String roomName;
    private Boolean workoutPlane;

    /**
     * Empty constructor
     */
    public Reservation() {
    }

    /**
     * Constructs a {@link Reservation} object assigning the attributes that are received by parameter
     * @param reservationCode
     * @param date
     * @param userCode references the {@link User} that owns the reservtion
     * @param roomName
     * @param workoutPlane
     */
    public Reservation(int reservationCode, Date date, int userCode, String roomName, Boolean workoutPlane) {
        this.reservationCode = reservationCode;
        this.date = date;
        this.userCode = userCode;
        this.roomName = roomName;
        this.workoutPlane = workoutPlane;
    }

    /**
     * Constructs a {@link Reservation} object assigning the attributes that are received by parameter
     * @param date
     * @param userCode references the {@link User} that owns the reservtion
     * @param roomName
     * @param workoutPlane
     */
    public Reservation( Date date, int userCode, String roomName, Boolean workoutPlane) {
        this.date = date;
        this.userCode = userCode;
        this.roomName = roomName;
        this.workoutPlane = workoutPlane;
    }

    /**
     * Method to get the {@link Reservation}'s code
     * @return the reservations code
     */
    public int getReservationCode() {
        return reservationCode;
    }

    /**
     * Method to set the {@link Reservation}'s code
     * @param reservationCode
     */
    public void setReservationCode(int reservationCode) {
        this.reservationCode = reservationCode;
    }

    /**
     * Method to get the date of the {@link Reservation}
     * @return
     */
    public Date getDate() {
        return date;
    }

    /**
     * Method to set the date of the {@link Reservation}
     * @param date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Method to get the id of the {@link User} owner
     * @return
     */
    public int getUserCode() {
        return userCode;
    }

    /**
     * Method to set the id of the {@link User} owner
     * @param userCode
     */
    public void setUserCode(int userCode) {
        this.userCode = userCode;
    }

    /**
     * Method to get the {@link Reservation}'s room name
     * @return
     */
    public String getRoomName() {
        return roomName;
    }

    /**
     * Method to set the {@link Reservation}'s room name
     * @param roomName
     */
    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    /**
     * Check if the {@link Reservation} includes a workout plane
     * @return true if the {@link Reservation} has a workout plane
     */
    public Boolean getWorkoutPlane() {
        return workoutPlane;
    }

    /**
     * Set if the {@link Reservation} includes a workout plane
     * @param workoutPlane
     */
    public void setWorkoutPlane(Boolean workoutPlane) {
        this.workoutPlane = workoutPlane;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("reservationCode: ").append(reservationCode).append(System.lineSeparator());
        sb.append("Date: ").append(dateToString(date)).append(System.lineSeparator());
        sb.append("UserCode: ").append(userCode).append(System.lineSeparator());
        sb.append("RoomName: ").append(roomName).append(System.lineSeparator());
        sb.append("WorkoutPlane: ").append(workoutPlane).append(System.lineSeparator()).append(System.lineSeparator());
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return reservationCode == that.reservationCode;
    }

    @Override
    public int hashCode() {
        return Objects.hash(reservationCode);
    }

    /**
     * Gets a date and parse it to String with a simplier format
     * @param date
     * @return date in String format
     */
    public String dateToString(Date date){
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return dateFormat.format(date);
    }
}
