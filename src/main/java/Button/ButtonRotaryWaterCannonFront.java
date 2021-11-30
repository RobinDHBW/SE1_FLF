package Button;

public class ButtonRotaryWaterCannonFront extends ButtonRotary {
    private final Integer stepSize = 500;

    @Override
    public void rotateLeft() {
        if (amountPerIteration > 500) amountPerIteration -= stepSize;
    }

    @Override
    public void rotateRight() {
        if (amountPerIteration < 3500) amountPerIteration += stepSize;
    }
}
