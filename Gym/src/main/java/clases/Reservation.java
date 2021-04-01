package clases;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/***
 * @author Ronald
 */


public class Reservation {
    private int reservationCode;
    private Date date;
    private int userCode;
    private String roomName;
    private Boolean workoutPlane;

    public Reservation() {
    }

    public Reservation(int reservationCode, Date date, int userCode, String roomName, Boolean workoutPlane) {
        this.reservationCode = reservationCode;
        this.date = date;
        this.userCode = userCode;
        this.roomName = roomName;
        this.workoutPlane = workoutPlane;
    }

    public Reservation( Date date, int userCode, String roomName, Boolean workoutPlane) {
        this.date = date;
        this.userCode = userCode;
        this.roomName = roomName;
        this.workoutPlane = workoutPlane;
    }



    public int getReservationCode() {
        return reservationCode;
    }

    public void setReservationCode(int reservationCode) {
        this.reservationCode = reservationCode;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getUserCode() {
        return userCode;
    }

    public void setUserCode(int userCode) {
        this.userCode = userCode;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Boolean getWorkoutPlane() {
        return workoutPlane;
    }

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

    public String dateToString(Date date){
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return dateFormat.format(date);
    }
}
