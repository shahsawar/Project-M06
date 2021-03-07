package sample;

import java.util.Date;

/***
 * @author Ronald
 */

public class Reservation {
    private Date date;
    private int userCode;
    private String roomName;
    private WorkoutPlane workoutPlane;

    public Reservation(Date date, int userCode, String roomName, WorkoutPlane workoutPlane) {
        this.date = date;
        this.userCode = userCode;
        this.roomName = roomName;
        this.workoutPlane = workoutPlane;
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

    public WorkoutPlane getWorkoutPlan() {
        return workoutPlane;
    }

    public void setWorkoutPlan(WorkoutPlane workoutPlane) {
        this.workoutPlane = workoutPlane;
    }
}
