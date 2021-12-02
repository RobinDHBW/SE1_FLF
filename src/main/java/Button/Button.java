package Button;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public abstract class Button {
    protected Object operatingDevice;

    public Button(Object operatingDevice) {
        this.operatingDevice = operatingDevice;
    }

    public void operateDevice() {

    }

    public Object getOperatingDevice() {
        return operatingDevice;
    }
}
