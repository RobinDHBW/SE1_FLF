package Drive;

public class AxleSteerable extends Axle implements IAxleSteerable {
    private Integer steeringAngle = 0;
    private Integer stepSize;

    public AxleSteerable(Integer stepSize) {
        this.stepSize = stepSize;
    }

    @Override
    public Integer steer(Boolean steerLeft) {
        return this.steeringAngle += this.stepSize * (steerLeft ? -1 : 1);
    }
}
