package Drive;

public class AxleSteerable extends Axle implements IAxleSteerable {
    private Integer steeringAngle = 0;

    public AxleSteerable() {

    }

    @Override
    public Integer steer(Integer degree) {
        return this.steeringAngle = degree;
    }
}
