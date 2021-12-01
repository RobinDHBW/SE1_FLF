package Joystick;

import Button.ButtonPress;
import Button.ButtonPush;
import Button.IButtonListener;

public abstract class Joystick implements IButtonListener {
    public ButtonPush btnPush = new ButtonPush();
    public ButtonPress btnPressLeft = new ButtonPress();
    public ButtonPress btnPressRight = new ButtonPress();

    public Joystick(){
        btnPush.addListener(this);
        btnPressLeft.addListener(this);
        btnPressRight.addListener(this);
    }


}
