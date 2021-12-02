package Drive;

public class ElectricEngine {

    private Boolean state = false;
    private Integer stepSize;

    public ElectricEngine(Integer stepSize) {
        this.stepSize = stepSize;
    }

    public Integer accelerate() {
        return this.stepSize * -1;
    }

}
