package Joystick;

import Button.*;

public class JoystickDriver extends Joystick{

    public JoystickDriver() {

    }

    @Override
    public void onToggleButton(Button o) {
        ButtonBoolean toggled = (ButtonBoolean) o;

    }
}
