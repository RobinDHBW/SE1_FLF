package Button;

public class ButtonSwitch extends ButtonBoolean {
    private SwitchDevice device;

    public ButtonSwitch(SwitchDevice device) {
        this.device = device;
    }

    public SwitchDevice getDevice() {
        return device;
    }
}
