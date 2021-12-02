package Axle;

public class Axle implements IAxle {
    protected Integer stepSize;
    protected Integer actualSpeed;


    public Axle() {
        this.stepSize = 4;
        this.actualSpeed = 0;
    }

    @Override
    public Integer accelerate() {
        actualSpeed += stepSize;
        return actualSpeed;
    }

    @Override
    public Integer brake(Integer actualSpeed) {
        Integer speed = actualSpeed-stepSize;
        return speed;
    }
}
