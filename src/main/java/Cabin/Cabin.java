package Cabin;

import Button.*;
import Instruments.BatteryIndicator;
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

    private final BusDoor busDoorLeft;
    private final BusDoor busDoorRight;


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

        this.busDoorLeft = built.busDoorLeft;
        this.busDoorRight = built.busDoorRight;

    }

    public static class Builder {
        private final CentralUnit centralUnit = new CentralUnit();

        private final BatteryIndicator batteryIndicator = new BatteryIndicator();
        private final Speedometer speedometer = new Speedometer();

        private final Pedal gasPedal;
        private final Pedal brakePedal;

        private final SteeringWheel steeringWheel;

        private final ButtonRotaryWaterCannonRoof btnRotaryWaterCannonRoof;
        private final ButtonRotaryWaterCannonFront btnRotaryWaterCannonFront;

        private final ControlPanel ctrlPanel;

        private final List<Seat> seatList = new ArrayList<>();

        private final Joystick joystickDriver;
        private final Joystick joystickOperator;

        private final BusDoor busDoorLeft = new BusDoor(VehicleSide.LEFT);
        private final BusDoor busDoorRight = new BusDoor(VehicleSide.RIGHT);


        public Builder(
                List<ButtonSwitch> switches,
                Pedal gasPedal,
                Pedal brakePedal,
                ButtonRotaryWaterCannonRoof btnRotaryWaterCannonRoof,
                ButtonRotaryWaterCannonFront btnRotaryWaterCannonFront,
                Joystick joystickDriver,
                Joystick joystickOperator,
                SteeringWheel steeringWheel
        ) {


            for (int i = 0; i < 2; i++) {
                Boolean leftSide = i == 0;
                seatList.add(new Seat(1, leftSide));
            }
            seatList.add(new SeatFirefighting(new Driver(), true));
            seatList.add(new SeatFirefighting(new Operator(), false));

            this.ctrlPanel = new ControlPanel.Builder(switches).build();

            this.btnRotaryWaterCannonRoof = btnRotaryWaterCannonRoof;
            this.btnRotaryWaterCannonFront = btnRotaryWaterCannonFront;

            this.joystickDriver = joystickDriver;
            this.joystickOperator = joystickOperator;

            this.steeringWheel = steeringWheel;

            this.gasPedal = gasPedal;
            this.brakePedal = brakePedal;
        }

        public Cabin build() {
            return new Cabin(this);
        }
    }
}