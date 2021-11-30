import Instruments.BatteryIndicator;
import Instruments.Pedal;
import Instruments.Speedometer;
import Instruments.SteeringWheel;

public class Cabin {
    private BatteryIndicator batteryIndicator = new BatteryIndicator();
    private Speedometer speedometer = new Speedometer();
    private Pedal gasPedal = new Pedal(0);
    private Pedal brakePedal = new Pedal(0);
    private SteeringWheel steeringWheel = new SteeringWheel();

    
    public Cabin(){

    }

}