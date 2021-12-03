package Instruments;

import Button.Button;

public class SteeringWheel extends Button {
    private Integer degree = 0;

    public SteeringWheel(Object o) {
        super(o);
    }

    public void steer(Boolean isLeft, Integer degree) {
        this.degree += degree * (isLeft ? -1 : 1);
    }

    public Integer getDegree() {
        return degree;
    }
}
