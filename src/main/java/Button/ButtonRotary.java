package Button;

public abstract class ButtonRotary extends Button implements IButtonRotary {
    protected Integer amountPerIteration;

    public ButtonRotary(Object o, Integer smallesAmount) {
        super(o);
        this.amountPerIteration = smallesAmount;
    }
}
