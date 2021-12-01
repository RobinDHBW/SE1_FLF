package Button;

public abstract class ButtonBoolean extends Button implements IButtonBoolean{
    protected Boolean isOn = false;

    public ButtonBoolean(){

    }

    @Override
    public void turnOn() {
        this.isOn=true;
    }

    @Override
    public void turnOff() {
        this.isOn=false;
    }

    public Boolean getOn() {
        return isOn;
    }
}
