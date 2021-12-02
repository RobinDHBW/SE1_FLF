package Joystick;

import Button.*;

public class JoystickOperator extends Joystick {
    JoystickType joystickType;

    public JoystickOperator(JoystickType joystickType) {
        this.joystickType = joystickType;
    }

    @Override
    public void onToggle(Button o) {

        if(this.joystickType == JoystickType.CLASSIC) {
            if(o.equals(this.btnPressLeft)){
                /**
                 *@TODO if(roofcannon.state==false) -> activate | vice versa
                 */

            }else if (o.equals(this.btnPressRight)){
                /**
                 * @TODO if(roofcannon.state==false) -> nothing
                 * @TODO if(roofcannon.state==true) -> change mixState (central unit)
                 */
            }else if(o.equals(this.btnPush)){
                /**
                 * @TODO if(roofcannon.state==false) -> nothing
                 * @TODO if(roofcannon.state==true) -> fire roofcannon mit eingestellter menge im 2. drehknopf
                 */
            }
        } else if(this.joystickType == JoystickType.INTELLIGENT) {
            if(o.equals(this.btnPressInt) && this.btnPressInt.isHeld5seconds() ){
                /**
                 *@TODO if(roofcannon.state==false) -> activate
                 */
            } else if(o.equals(this.btnPressInt) && !this.btnPressInt.isHeld5seconds()){
                /**
                 *@TODO if(roofcannon.state==true) -> deactivate
                 */
            } else if(o.equals(this.btnPressInt)) {
                /**
                 * @TODO if(roofcannon.state==true) -> change mixState (central unit)
                 */

            } else if(o.equals(this.btnPush)) {
                /**
                 * @TODO if(roofcannon.state==true) -> fire roofcannon mit eingestellter menge im 2. drehknopf
                 */
            }
        }

    }


}
