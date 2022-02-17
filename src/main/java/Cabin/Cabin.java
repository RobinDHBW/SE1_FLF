package Cabin;

import Button.ButtonRotaryWaterCannonFront;
import Button.ButtonRotaryWaterCannonRoof;
import Button.Pedal;
import CentralUnit.CentralUnit;
import Instruments.BatteryIndicator;
import Instruments.Speedometer;
import Instruments.SteeringWheel;
import Joystick.Joystick;
import Person.*;
import Seating.Seat;
import Seating.SeatFirefighting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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

        this.busdoorLeft = builder.busdoorLeft;
        this.busdoorRight = builder.busdoorRight;

    }

    /**********
     * Getter
     *********/

    public List<Seat> getSeatList() {
        return seatList;
    }

    public BatteryIndicator getBatteryIndicator() {
        return batteryIndicator;
    }

    public Speedometer getSpeedometer() {
        return speedometer;
    }

    public Pedal getGasPedal() {
        return gasPedal;
    }

    public Pedal getBrakePedal() {
        return brakePedal;
    }

    public SteeringWheel getSteeringWheel() {
        return steeringWheel;
    }

    public ButtonRotaryWaterCannonRoof getBtnRotaryWaterCannonRoof() {
        return btnRotaryWaterCannonRoof;
    }

    public ButtonRotaryWaterCannonFront getBtnRotaryWaterCannonFront() {
        return btnRotaryWaterCannonFront;
    }

    public ControlPanel getCtrlPanel() {
        return ctrlPanel;
    }

    public CentralUnit getCentralUnit() {
        return centralUnit;
    }

    public Joystick getJoystickDriver() {
        return joystickDriver;
    }

    public Joystick getJoystickOperator() {
        return joystickOperator;
    }

    public Busdoor getBusDoorLeft() {
        return busdoorLeft;
    }

    public Busdoor getBusDoorRight() {
        return busdoorRight;
    }

    public void toggleLeftDoor(Boolean fromOutside) {
        this.getBusDoorLeft().toggleDoor(fromOutside);
    }

    public void toggleRightDoor(Boolean fromOutside) {
        this.getBusDoorRight().toggleDoor(fromOutside);
    }

    public void enterCabin(Person enterer, Boolean isLeft) {
        try {
            if (!(isLeft ? this.getBusDoorLeft() : this.getBusDoorRight()).getOpen())
                throw new Exception("Door not open");
            for (Seat seat : seatList) {
                if (seat.getOccupied()) continue;

                if(enterer instanceof ActivePassenger){
                    if (seat instanceof SeatFirefighting && enterer.getClass().equals(((SeatFirefighting) seat).getPersonAllowed().getClass())) {
                        seat.sitDown(enterer);
                        enterer.setIsInVehicle(true);
                        if (enterer instanceof Driver) {
                            ((Driver) enterer).equip(this.steeringWheel, this.gasPedal, this.brakePedal, this.joystickDriver);
                        } else {
                            ((Operator) enterer).equip(this.ctrlPanel, this.joystickOperator, this.btnRotaryWaterCannonFront, this.btnRotaryWaterCannonRoof);
                        }
                    }
                }else{
                    if (!(seat instanceof SeatFirefighting) && seat.getLeftSide() == isLeft) {
                        seat.sitDown(enterer);
                        enterer.setIsInVehicle(true);
                    }
                }
            }

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            System.err.println(Arrays.toString(ex.getStackTrace()));
        }
    }

    public Person leaveCabin(Integer row, Boolean isLeft) {
        try {
            if (!(isLeft ? this.getBusDoorLeft() : this.getBusDoorRight()).getOpen())
                throw new Exception("Door not open");

            for (Seat seat : seatList) {
                if (Objects.equals(seat.getSeatRow(), row) && seat.getLeftSide() == isLeft) return seat.leave();
            }
            return null;
        }catch (Exception ex){
            System.err.println(ex.getMessage());
            System.err.println(Arrays.toString(ex.getStackTrace()));
            return null;
        }
    }

    public static class Builder {
        private final CentralUnit centralUnit;

        private final BatteryIndicator batteryIndicator;// = new BatteryIndicator();
        private final Speedometer speedometer;// = new Speedometer();

        private final Pedal gasPedal;
        private final Pedal brakePedal;

        private final SteeringWheel steeringWheel;

        private final ButtonRotaryWaterCannonRoof btnRotaryWaterCannonRoof;
        private final ButtonRotaryWaterCannonFront btnRotaryWaterCannonFront;

        private final ControlPanel ctrlPanel;

        private final List<Seat> seatList = new ArrayList<>();

        private final Joystick joystickDriver;
        private final Joystick joystickOperator;

        private final Busdoor busdoorLeft;
        private final Busdoor busdoorRight;


        public Builder(
                ControlPanel controlPanel,
                Pedal gasPedal,
                Pedal brakePedal,
                ButtonRotaryWaterCannonRoof btnRotaryWaterCannonRoof,
                ButtonRotaryWaterCannonFront btnRotaryWaterCannonFront,
                Joystick joystickDriver,
                Joystick joystickOperator,
                SteeringWheel steeringWheel,
                Speedometer speedometer,
                CentralUnit centralUnit,
                BatteryIndicator batteryIndicator,
                Busdoor busdoorLeft,
                Busdoor busdoorRight
        ) {
            this.busdoorLeft = busdoorLeft;
            this.busdoorRight = busdoorRight;
            for (int i = 0; i < 2; i++) {
                Boolean leftSide = (i == 0);
                seatList.add(new Seat(1, leftSide));
            }
            seatList.add(new SeatFirefighting(new Driver("abc"), true));
            seatList.add(new SeatFirefighting(new Operator("abc"), false));

            this.ctrlPanel = controlPanel;

            this.btnRotaryWaterCannonRoof = btnRotaryWaterCannonRoof;
            this.btnRotaryWaterCannonFront = btnRotaryWaterCannonFront;

            this.joystickDriver = joystickDriver;
            this.joystickOperator = joystickOperator;

            this.steeringWheel = steeringWheel;

            this.gasPedal = gasPedal;
            this.brakePedal = brakePedal;
            this.centralUnit = centralUnit;
            this.speedometer = speedometer;
            this.batteryIndicator = batteryIndicator;
        }

        public Cabin build() {
            return new Cabin(this);
        }
    }
}