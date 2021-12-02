package Button;

public abstract class ButtonBoolean extends Button implements IButtonBoolean {
    protected Boolean isOn = false;

    public ButtonBoolean(Object o) {
        super(o);
    }

    @Override
    public void turnOn() {
        this.isOn = true;
    }

    @Override
    public void turnOff() {
        this.isOn = false;
    }

    public Boolean getOn() {
        return isOn;
    }
}
