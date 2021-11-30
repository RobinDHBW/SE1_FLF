package Button;

public abstract class ButtonRotary implements IButtonRotary {
    protected Integer amountPerIteration;

    public ButtonRotary() {

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
