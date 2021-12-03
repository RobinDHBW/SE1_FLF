package Button;

import Firefighting.WaterCannonFront;
import Tank.MixingProcessor;

public class ButtonRotaryWaterCannonFront extends ButtonRotary {
    private final Integer stepSize = 500;

    public ButtonRotaryWaterCannonFront(MixingProcessor o) {
        super(o);
    }

    @Override
    public void rotateLeft() {
        if (amountPerIteration > 500) amountPerIteration -= stepSize;
        this.operateDevice();
    }

    @Override
    public void rotateRight() {
        if (amountPerIteration < 3500) amountPerIteration += stepSize;
        this.operateDevice();
    }
}
