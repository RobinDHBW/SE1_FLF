package Firefighting;

public class WaterCannonFront extends WaterCannon {
    Integer rotationStepSize, position = 0;

    public WaterCannonFront(Integer sprayCapacityPerlIteration, Integer rotationStepSize) {
        super(sprayCapacityPerlIteration);
        this.rotationStepSize = rotationStepSize;
    }

    public void rotateLeft() {
        if (position > 0) position -= rotationStepSize;
    }


    public void rotateRight() {
        if (position < 180) position += rotationStepSize;
    }

    public Integer getPosition() {
        return position;
    }
}
