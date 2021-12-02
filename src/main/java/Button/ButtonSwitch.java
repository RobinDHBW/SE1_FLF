package Button;

public class ButtonSwitch extends ButtonBoolean {
    private SwitchDevice device;

    public ButtonSwitch(Object o, SwitchDevice device) {
        super(o);
        this.device = device;
    }

    public SwitchDevice getDevice() {
        return device;
    }
}
