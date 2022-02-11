package Button;

public abstract class ButtonRotary extends Button implements IButtonRotary {
    protected Integer amountPerIteration=0;

    public ButtonRotary(Object o, Integer smallesAmount) {
        super(o);
        this.amountPerIteration = smallesAmount;
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
