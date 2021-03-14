package clases;

import java.util.Date;

/***
 * @author Ronald
 */


public class Reservation {
    private int reservationCode;
    private Date date;
    private int userCode;
    private String roomName;
<<<<<<< HEAD
    boolean workoutPlane;

    public Reservation(){

    }

    public Reservation(int reservationCode, Date date, int userCode, String roomName, boolean workoutPlane) {
        this.reservationCode = reservationCode;
        this.date = date;
        this.userCode = userCode;
        this.roomName = roomName;
        this.workoutPlane = workoutPlane;
    }

    public Reservation(Date date, int userCode, String roomName, boolean workoutPlane) {
=======
    private Boolean workoutPlane;

    public Reservation() {
    }


    public Reservation(int reservationCode, Date date, int userCode, String roomName, Boolean workoutPlane) {
>>>>>>> main
        this.reservationCode = reservationCode;
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

<<<<<<< HEAD
    public boolean hasWorkoutPlane() {
        return workoutPlane;
    }

    public void setWorkoutPlane(boolean workoutPlane) {
=======
    public Boolean getWorkoutPlane() {
        return workoutPlane;
    }

    public void setWorkoutPlane(Boolean workoutPlane) {
>>>>>>> main
        this.workoutPlane = workoutPlane;
    }
}
