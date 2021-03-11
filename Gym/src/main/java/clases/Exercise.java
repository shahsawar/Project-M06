package clases;

/***
 * @author shah
 */
public class Exercise {

    private String name;
    private String description;
    private String targetMuscles;

    /***
     * Constructor
     * @param name
     * @param description
     * @param targetMuscles
     */
    public Exercise(String name, String description, String targetMuscles) {
        this.name = name;
        this.description = description;
        this.targetMuscles = targetMuscles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTargetMuscles() {
        return targetMuscles;
    }

    public void setTargetMuscles(String targetMuscles) {
        this.targetMuscles = targetMuscles;
    }
}