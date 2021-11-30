package Button;

public abstract class ButtonBoolean implements IButtonBoolean{
    protected Boolean toggleState = false;

    public ButtonBoolean(){

    }

    @Override
    public void turnOn() {
        this.toggleState=true;
    }

    @Override
    public void turnOff() {
        this.toggleState=false;
    }

    @Override
    public Boolean isOn(){
        return toggleState;
    }
}
