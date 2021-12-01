package Joystick;

import Button.*;

import java.util.function.Function;

public class JoystickDriver extends Joystick{

    public JoystickDriver() {
    }



    @Override
    public void onToggleButton(Button o) {

        if(o.equals(this.btnPressLeft)){
            /**
             *@TODO if(frontcannon.state==false) -> activate | vice versa
             */

        }else if (o.equals(this.btnPressRight)){
            /**
             * @TODO if(frontcannon.state==false) -> nothing
             * @TODO if(frontcannon.state==true) -> change mixState (central unit)
             */
        }else if(o.equals(this.btnPush)){
            /**
             * @TODO if(frontcannon.state==false) -> nothing
             * @TODO if(frontcannon.state==true) -> fire frontcannon
             */
        }

    }
}
