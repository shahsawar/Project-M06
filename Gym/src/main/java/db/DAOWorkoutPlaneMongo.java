package db;

import clases.Reservation;
import clases.WorkoutPlane;
import org.bson.Document;

import java.util.List;

public class DAOWorkoutPlaneMongo implements DAOWorkOutPlane{
    @Override
    public void insert(WorkoutPlane workoutPlane) {

    }

    @Override
    public void delete(WorkoutPlane workoutPlane) {

    }

    @Override
    public void update(WorkoutPlane workoutPlane) {

    }

    @Override
    public List<WorkoutPlane> getAll() {
        return null;
    }

    @Override
    public WorkoutPlane getByIdentifier(String s) {
        return null;
    }

    public static Document toDoc(WorkoutPlane workoutPlane){
        Document docTmp = new Document();
        docTmp.append("name", workoutPlane.getName());
        docTmp.append("frequency", workoutPlane.getFrequency());
        return docTmp;
    }
}
