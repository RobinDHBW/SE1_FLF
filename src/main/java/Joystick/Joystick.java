package Joystick;

import Button.ButtonPress;
import Button.ButtonPush;

public class Joystick {
    private final ButtonPush btnPush;
    private final ButtonPress btnPressLeft;
    private final ButtonPress btnPressRight;
    //public ButtonPress btnPressInt = new ButtonPress();

    public Joystick(ButtonPush btnPush, ButtonPress btnPressLeft, ButtonPress btnPressRight) {
        //btnPressInt.addListener(this);
        this.btnPush = btnPush;
        this.btnPressLeft = btnPressLeft;
        this.btnPressRight = btnPressRight;
    }

    public void pressBtnLeft() {
        this.btnPressLeft.operateDevice();
    }

    public void pressBtnRight() {
        this.btnPressRight.operateDevice();
    }

    public void pushBtn() {
        this.btnPush.operateDevice();
    }
}
