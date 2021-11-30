package Cabin;

import Instruments.BatteryIndicator;
import Instruments.Pedal;
import Instruments.Speedometer;
import Instruments.SteeringWheel;

public class Cabin {
    private final BatteryIndicator batteryIndicator;
    private final Speedometer speedometer;
    private final Pedal gasPedal ;
    private final Pedal brakePedal;
    private final SteeringWheel steeringWheel;


    private Cabin(Builder builder) {
        Cabin built = builder.build();
        this.batteryIndicator = built.batteryIndicator;
        this.speedometer = built.speedometer;
        this.gasPedal= built.gasPedal;
        this.brakePedal= built.brakePedal;
        this.steeringWheel= built.steeringWheel;
    }

    public static class Builder {
        private final BatteryIndicator batteryIndicator = new BatteryIndicator();
        private final Speedometer speedometer = new Speedometer();
        private final Pedal gasPedal = new Pedal(0);
        private final Pedal brakePedal = new Pedal(0);
        private final SteeringWheel steeringWheel = new SteeringWheel();

        public Builder() {

        }

        public Cabin build() {
            return new Cabin(this);
        }
    }


}