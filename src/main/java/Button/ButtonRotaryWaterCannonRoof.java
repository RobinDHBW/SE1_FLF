package Button;

public class ButtonRotaryWaterCannonRoof extends ButtonRotary {
    private RoofCannonMode mode = RoofCannonMode.A;

    public ButtonRotaryWaterCannonRoof() {

    }

    public RoofCannonMode getMode() {
        return mode;
    }

    private void setParam(Integer amount, RoofCannonMode mode) {
        this.mode = mode;
        this.amountPerIteration = amount;
    }

    @Override
    public void rotateLeft() {
        switch (mode) {
            case C -> this.setParam(1000, RoofCannonMode.B);
            case A, B -> this.setParam(500, RoofCannonMode.A);
        }
        ;
    }

    @Override
    public void rotateRight() {
        mode = switch (mode) {
            case A -> RoofCannonMode.B;
            case B, C -> mode = RoofCannonMode.C;
        };
    }
}
