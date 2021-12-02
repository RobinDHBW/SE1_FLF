package Joystick;

import Button.Button;
import Button.ButtonPress;
import Button.ButtonPush;
import Button.IButtonListener;

public abstract class Joystick implements IButtonListener {
    public ButtonPush btnPush = new ButtonPush();
    public ButtonPress btnPressLeft = new ButtonPress();
    public ButtonPress btnPressRight = new ButtonPress();
    public ButtonPress btnPressInt = new ButtonPress();

    public Joystick(){
        btnPush.addListener(this);
        btnPressLeft.addListener(this);
        btnPressRight.addListener(this);
        btnPressInt.addListener(this);
    }

}
