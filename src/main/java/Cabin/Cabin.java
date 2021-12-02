package Cabin;

import Button.ButtonRotaryWaterCannonFront;
import Button.ButtonRotaryWaterCannonRoof;
import Button.PedalType;
import Instruments.BatteryIndicator;
import Button.Pedal;
import Instruments.Speedometer;
import Instruments.SteeringWheel;
import Joystick.*;
import Person.Driver;
import Person.Operator;
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
    private final CentralUnit centralUnit;

    private final Joystick joystickDriver;
    private final Joystick joystickOperator;

    private final Busdoor busdoorLeft;
    private final Busdoor busdoorRight;


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
        this.centralUnit = built.centralUnit;

        this.joystickDriver = built.joystickDriver;
        this.joystickOperator = built.joystickOperator;

        this.busdoorLeft = built.busdoorLeft;
        this.busdoorRight = built.busdoorRight;

    }

    public static class Builder {
        private final CentralUnit centralUnit = new CentralUnit();

        private final BatteryIndicator batteryIndicator = new BatteryIndicator();
        private final Speedometer speedometer = new Speedometer();

        private final Pedal gasPedal = new Pedal(PedalType.ACCELERATE, 4);
        private final Pedal brakePedal = new Pedal(PedalType.BREAK, 4);

        private final SteeringWheel steeringWheel = new SteeringWheel();

        private final ButtonRotaryWaterCannonRoof btnRotaryWaterCannonRoof = new ButtonRotaryWaterCannonRoof();
        private final ButtonRotaryWaterCannonFront btnRotaryWaterCannonFront = new ButtonRotaryWaterCannonFront();

        private final ControlPanel ctrlPanel = new ControlPanel.Builder().build();


        private final List<Seat> seatList = new ArrayList<>();

        private final Joystick joystickDriver = new JoystickDriver(JoystickType.CLASSIC);
        private final Joystick joystickOperator = new JoystickOperator(JoystickType.CLASSIC);

        private final Busdoor busdoorLeft = new Busdoor(VehicleSide.LEFT);
        private final Busdoor busdoorRight = new Busdoor(VehicleSide.RIGHT);


        public Builder() {
            for (int i = 0; i < 2; i++) {
                Boolean leftSide = i == 0;
                seatList.add(new Seat(1, leftSide));
            }
            seatList.add(new SeatFirefighting(new Driver(), true));
            seatList.add(new SeatFirefighting(new Operator(), false));
        }

        public Cabin build() {
            return new Cabin(this);
        }
    }
}