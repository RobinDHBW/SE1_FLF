package Button;

import java.util.ArrayList;
import java.util.List;

public abstract class Button {
    private List<IButtonListener> listeners = new ArrayList<IButtonListener>();

    public Button() {

    }

    public void addListener(IButtonListener lug) {
        listeners.add(lug);
    }

    public void toggle() {
        for (IButtonListener listener : listeners) {
            listener.onToggleButton(this);
        }
    }
}
