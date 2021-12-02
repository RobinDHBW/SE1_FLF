package Joystick;

import Button.*;

import java.util.function.Function;

public class JoystickDriver extends Joystick{
    JoystickType joystickType;

    public JoystickDriver(JoystickType joystickType) {
        this.joystickType = joystickType;
    }


    @Override
    public void onToggle(Button o) {

        if(this.joystickType == JoystickType.CLASSIC) {
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
                 * @TODO if(frontcannon.state==true) -> fire frontcannon mit eingestellter menge im 1. drehknopf
                 */
            }
        } else if(this.joystickType == JoystickType.INTELLIGENT) {
            if(o.equals(this.btnPressInt) && this.btnPressInt.isHeld5seconds() ){
                /**
                 *@TODO if(frontcannon.state==false) -> activate
                 */
            } else if(o.equals(this.btnPressInt) && !this.btnPressInt.isHeld5seconds()){
                /**
                 *@TODO if(frontcannon.state==true) -> deactivate
                 */
            } else if(o.equals(this.btnPressInt)) {
                /**
                 * @TODO if(frontcannon.state==true) -> change mixState (central unit)
                 */

            } else if(o.equals(this.btnPush)) {
                /**
                 * @TODO if(frontcannon.state==true) -> fire frontcannon mit eingestellter menge im 1. drehknopf
                 */
            }
        }


    }
}
