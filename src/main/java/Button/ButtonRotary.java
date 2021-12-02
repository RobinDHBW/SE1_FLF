package Button;

public abstract class ButtonRotary extends Button implements IButtonRotary {
    protected Integer amountPerIteration;

    public ButtonRotary(Object o) {
        super(o);
    }

    /**
     * @return amountPerIteration
     */
    public Integer getAmountPerIteration() {
        return amountPerIteration;
    }

    /**
     * @param amount
     */
    protected void setAmountPerIteration(Integer amount) {
        this.amountPerIteration = amount;
    }
}
