package Drive;

public class DiscBrake {
    private Double stepSize;

    public DiscBrake(Double stepSize) {
        this.stepSize = stepSize;
    }

    public Double brake() {
        return this.stepSize;
    }
}
