package Axle;

public class AxleSteerable extends Axle implements IAxleSteerable {
    protected Double steeringAngle;

    public AxleSteerable() {
        this.steeringAngle = 0.0;
    }

    @Override
    public void steer(Double degree) {
        steeringAngle = degree;
    }
}
