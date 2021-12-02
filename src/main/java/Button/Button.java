package Button;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public abstract class Button {
    private List<IButtonListener> listeners = new ArrayList<>();
    private Object operatingDevice;

    public Button(Object operatingDevice) {
        this.operatingDevice = operatingDevice;
    }

    public void addListener(IButtonListener lug) {
        listeners.add(lug);
    }

    public void toggle() {
        for (IButtonListener listener : listeners) {
            listener.onToggle(this);
        }
    }

    //public void operateDevice(Function<Object, Object> callback){
    //
    //}

    public Object getOperatingDevice() {
        return operatingDevice;
    }
}
