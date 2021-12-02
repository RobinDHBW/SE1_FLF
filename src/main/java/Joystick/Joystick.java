package Joystick;

import Button.Button;
import Button.ButtonPress;
import Button.ButtonPush;
import Button.IButtonListener;

public class Joystick {
    public ButtonPush btnPush;
    public ButtonPress btnPressLeft;
    public ButtonPress btnPressRight;
    //public ButtonPress btnPressInt = new ButtonPress();

    public Joystick(ButtonPush btnPush, ButtonPress btnPressLeft, ButtonPress btnPressRight) {
        //btnPressInt.addListener(this);
        this.btnPush = btnPush;
        this.btnPressLeft = btnPressLeft;
        this.btnPressRight = btnPressRight;
    }
}
