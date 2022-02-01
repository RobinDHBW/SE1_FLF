package Person;

import Cabin.ControlPanel;
import Joystick.Joystick;

public class Operator extends Person {
    private ControlPanel panel;
    private Joystick opJoystick;

    public Operator() {
    }

    public void equip(ControlPanel panel, Joystick opJoystick){
        this.panel =panel;
        this.opJoystick=opJoystick;
    }
}