package sample;

/***
 * @author shah
 */
public class WorkoutPlane {

    private String name;
    private int frequency;

    /***
     *
     * @param name
     * @param frequency
     */
    public WorkoutPlane(String name, int frequency) {
        this.name = name;
        this.frequency = frequency;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }
}
