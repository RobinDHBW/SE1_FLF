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
        //Cabin built = builder.build();
        this.seatList = builder.seatList;

        this.batteryIndicator = builder.batteryIndicator;
        this.speedometer = builder.speedometer;

        this.gasPedal = builder.gasPedal;
        this.brakePedal = builder.brakePedal;

        this.steeringWheel = builder.steeringWheel;

        this.btnRotaryWaterCannonRoof = builder.btnRotaryWaterCannonRoof;
        this.btnRotaryWaterCannonFront = builder.btnRotaryWaterCannonFront;

        this.ctrlPanel = builder.ctrlPanel;
        this.centralUnit = builder.centralUnit;

        this.joystickDriver = builder.joystickDriver;
        this.joystickOperator = builder.joystickOperator;

        this.busDoorLeft = builder.busDoorLeft;
        this.busDoorRight = builder.busDoorRight;

    }

    public void accelerate() {
        this.speedometer.setSpeed(this.centralUnit.accelerate());
    }

    public void brake() {
        this.speedometer.setSpeed(this.centralUnit.brake());
    }

    public void drive() {
        this.speedometer.setSpeed(this.centralUnit.drive());
    }

    public static class Builder {
        private final CentralUnit centralUnit;

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
                ControlPanel controlPanel,
                Pedal gasPedal,
                Pedal brakePedal,
                ButtonRotaryWaterCannonRoof btnRotaryWaterCannonRoof,
                ButtonRotaryWaterCannonFront btnRotaryWaterCannonFront,
                Joystick joystickDriver,
                Joystick joystickOperator,
                SteeringWheel steeringWheel,
                CentralUnit centralUnit
        ) {
            for (int i = 0; i < 2; i++) {
                Boolean leftSide = i == 0;
                seatList.add(new Seat(1, leftSide));
            }
            seatList.add(new SeatFirefighting(new Driver(), true));
            seatList.add(new SeatFirefighting(new Operator(), false));

            this.ctrlPanel = controlPanel;

            this.btnRotaryWaterCannonRoof = btnRotaryWaterCannonRoof;
            this.btnRotaryWaterCannonFront = btnRotaryWaterCannonFront;

            this.joystickDriver = joystickDriver;
            this.joystickOperator = joystickOperator;

            this.steeringWheel = steeringWheel;

            this.gasPedal = gasPedal;
            this.brakePedal = brakePedal;
            this.centralUnit = centralUnit;
        }

        public Cabin build() {
            return new Cabin(this);
        }
    }
}