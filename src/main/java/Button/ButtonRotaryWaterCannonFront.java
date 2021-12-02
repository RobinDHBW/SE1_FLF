package Button;

import Firefighting.WaterCannonFront;

public class ButtonRotaryWaterCannonFront extends ButtonRotary {
    private final Integer stepSize = 500;

    public ButtonRotaryWaterCannonFront(WaterCannonFront o) {
        super(o);
    }

    @Override
    public void rotateLeft() {
        if (amountPerIteration > 500) amountPerIteration -= stepSize;
    }

    @Override
    public void rotateRight() {
        if (amountPerIteration < 3500) amountPerIteration += stepSize;
    }
}
