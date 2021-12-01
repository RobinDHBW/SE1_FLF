package Axle;

public class Axle implements IAxle {
    protected Integer stepSize;
    protected Integer actualSpeed;


    public Axle() {
        this.stepSize = 4;
        this.actualSpeed = 0;
    }

    @Override
    public int accelerate() {
        actualSpeed += stepSize;
        return actualSpeed;
    }

    @Override
    public int brake(Integer actualSpeed) {
        Integer speed = actualSpeed-stepSize;
        return speed;
    }
}
