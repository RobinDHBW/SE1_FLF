package Cabin;

import Button.ButtonRotaryWaterCannonFront;
import Button.ButtonRotaryWaterCannonRoof;
import ControlPanel.ControlPanel;
import Instruments.BatteryIndicator;
import Instruments.Pedal;
import Instruments.Speedometer;
import Instruments.SteeringWheel;
import Seating.Seat;
import Seating.SeatFirefighting;

import java.util.ArrayList;
import java.util.List;

public class Cabin {
    private final List<Seat> seatList;
    private final BatteryIndicator batteryIndicator;
    private final Speedometer speedometer;
    private final Pedal gasPedal;
    private final Pedal brakePedal;
    private final SteeringWheel steeringWheel;
    private final ButtonRotaryWaterCannonRoof btnRotaryWaterCannonRoof;
    private final ButtonRotaryWaterCannonFront btnRotaryWaterCannonFront;
    private final ControlPanel ctrlPanel;


    private Cabin(Builder builder) {
        Cabin built = builder.build();
        this.seatList = built.seatList;
        this.batteryIndicator = built.batteryIndicator;
        this.speedometer = built.speedometer;
        this.gasPedal = built.gasPedal;
        this.brakePedal = built.brakePedal;
        this.steeringWheel = built.steeringWheel;
        this.btnRotaryWaterCannonRoof = built.btnRotaryWaterCannonRoof;
        this.btnRotaryWaterCannonFront = built.btnRotaryWaterCannonFront;
        this.ctrlPanel = built.ctrlPanel;

    }

    public static class Builder {
        private final BatteryIndicator batteryIndicator = new BatteryIndicator();
        private final Speedometer speedometer = new Speedometer();
        private final Pedal gasPedal = new Pedal(0);
        private final Pedal brakePedal = new Pedal(0);
        private final SteeringWheel steeringWheel = new SteeringWheel();
        private final ButtonRotaryWaterCannonRoof btnRotaryWaterCannonRoof = new ButtonRotaryWaterCannonRoof();
        private final ButtonRotaryWaterCannonFront btnRotaryWaterCannonFront = new ButtonRotaryWaterCannonFront();
        private final ControlPanel ctrlPanel = new ControlPanel.Builder().build();
        private final List<Seat> seatList = new ArrayList<>();


        public Builder() {
            for (int i = 0; i < 2; i++) {
                boolean leftSide = i == 0;
                seatList.add(new Seat(1, leftSide, false));
                seatList.add(new SeatFirefighting(leftSide, false));
            }
        }

        public Cabin build() {
            return new Cabin(this);
        }
    }
}