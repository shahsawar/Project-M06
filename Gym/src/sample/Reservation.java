package sample;

import java.util.Date;

public class Reservation {
    private Date date;
    private int userCode;
    private String roomName;
    private WorkoutPlan workoutPlan;

    public Reservation(Date date, int userCode, String roomName, WorkoutPlan workoutPlan) {
        this.date = date;
        this.userCode = userCode;
        this.roomName = roomName;
        this.workoutPlan = workoutPlan;
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

    public WorkoutPlan getWorkoutPlan() {
        return workoutPlan;
    }

    public void setWorkoutPlan(WorkoutPlan workoutPlan) {
        this.workoutPlan = workoutPlan;
    }
}
