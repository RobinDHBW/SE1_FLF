package FLF;

import Button.*;
import Cabin.*;
import Drive.Drive;
import Firefighting.CannonIdentifier;
import Firefighting.WaterCannonFront;
import Firefighting.WaterCannonRoof;
import Firefighting.WaterDieSelfprotection;
import Instruments.SteeringWheel;
import Joystick.Joystick;
import Lights.*;
import Person.*;
import Seating.Seat;
import Tank.MixingProcessor;
import Tank.TankSubject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 */
public class FLF {
    private final List<SearchLight> searchLightsFront;
    private final List<SearchLight> searchLightsRoof;
    private final List<SearchLight> searchLightsSide;
    private final List<DirectionIndicator> directionIndicatorsLeft;
    private final List<DirectionIndicator> directionIndicatorsRight;
    private final List<BrakingLight> brakingLights;

    private final List<FlashingBlueLight> flashingBlueLights;
    private final List<WarningLight> warningLights;

    private final Cabin cabin;

    private final Drive drive;

    private final MixingProcessor mixingProcessor;

    private Boolean maintenanceState = false;


    /**********
     * Getter
     *********/

    public List<SearchLight> getSearchLightsFront() {
        return searchLightsFront;
    }

    public List<SearchLight> getSearchLightsRoof() {
        return searchLightsRoof;
    }

    public List<SearchLight> getSearchLightsSide() {
        return searchLightsSide;
    }

    public List<DirectionIndicator> getDirectionIndicatorsLeft() {
        return directionIndicatorsLeft;
    }

    public List<DirectionIndicator> getDirectionIndicatorsRight() {
        return directionIndicatorsRight;
    }

    public List<BrakingLight> getBrakingLights() {
        return brakingLights;
    }

    public List<FlashingBlueLight> getFlashingBlueLights() {
        return flashingBlueLights;
    }

    public List<WarningLight> getWarningLights() {
        return warningLights;
    }

    public Cabin getCabin() {
        return cabin;
    }

    public Drive getDrive() {
        return drive;
    }

    public MixingProcessor getMixingProcessor() {
        return mixingProcessor;
    }

    private FLF(Builder builder) {

        //FLF built = builder.build();
        this.brakingLights = builder.brakingLights;
        this.searchLightsFront = builder.searchLightsFront;
        this.searchLightsRoof = builder.searchLightsRoof;
        this.searchLightsSide = builder.searchLightsSide;
        this.directionIndicatorsLeft = builder.directionIndicatorsLeft;
        this.directionIndicatorsRight = builder.directionIndicatorsRight;

        this.flashingBlueLights = builder.flashingBlueLights;
        this.warningLights = builder.warningLights;

        this.cabin = builder.cabin;

        this.drive = builder.drive;

        this.mixingProcessor = builder.mixingProcessor;
    }

    public void toggleLeftDoor(Boolean fromOutside){this.cabin.toggleLeftDoor(fromOutside);}
    public void toggleRightDoor(Boolean fromOutside){this.cabin.toggleRightDoor(fromOutside);}

    public void enterFLF(Person enterer, Boolean isLeft) {this.cabin.enterCabin(enterer, isLeft);}

    public Person leaveFLF(Integer row, Boolean isLeft){
        return this.cabin.leaveCabin(row, isLeft);
    }

    public Boolean getSearchLightFrontState(){
        return this.searchLightsFront.get(0).getState();
    }

    public Boolean getSearchLightRoofState(){
        return this.searchLightsRoof.get(0).getState();
    }

    public Boolean getSearchLightSideState(){
        return this.searchLightsSide.get(0).getState();
    }

    public Boolean getBlueLightState(){
        return this.flashingBlueLights.get(0).getState();
    }

    public Boolean getWarnLightsState(){
        return this.warningLights.get(0).getState();
    }

    public void toggleMaintenance(EmployeeFirebase eFB){
        if(this.maintenanceState){
            eFB.uneqip();
        }else{
            eFB.equip(this.mixingProcessor, this.drive);
        }
        this.maintenanceState = !this.maintenanceState;
    }

    /**
     *
     */
    public static class Builder {

        private final List<WarningLight> warningLights = new ArrayList<>();
        private final List<DirectionIndicator> directionIndicatorsLeft = new ArrayList<>();
        private final List<DirectionIndicator> directionIndicatorsRight = new ArrayList<>();
        private final List<BrakingLight> brakingLights = new ArrayList<>();
        private final List<SearchLight> searchLightsFront = new ArrayList<>();
        private final List<SearchLight> searchLightsRoof = new ArrayList<>();
        private final List<SearchLight> searchLightsSide = new ArrayList<>();
        private final List<FlashingBlueLight> flashingBlueLights = new ArrayList<>();
        private final Cabin cabin;

        private final Drive drive = new Drive();

        private final MixingProcessor mixingProcessor = new MixingProcessor();

        public Builder() {

            buildLights();
            CentralUnit centralUnit = new CentralUnit(warningLights, flashingBlueLights, searchLightsFront, searchLightsRoof, searchLightsSide, directionIndicatorsLeft, directionIndicatorsRight, mixingProcessor, drive);

            Pedal pedalAcc = new Pedal(centralUnit) {
                @Override
                public void operateDevice() {
                    ((CentralUnit) this.operatingDevice).accelerate();
                }
            };
            Pedal pedalBrake = new Pedal(centralUnit) {
                @Override
                public void operateDevice() {
                    ((CentralUnit) this.operatingDevice).brake();
                }
            };

            ButtonRotaryWaterCannonFront btnCannonFront = new ButtonRotaryWaterCannonFront(this.mixingProcessor) {
                @Override
                public void operateDevice() {
                    ((MixingProcessor) this.operatingDevice).setSprayCapacityPerlIteration(CannonIdentifier.CANNON_FRONT, this.amountPerIteration);
                }
            };
            ButtonRotaryWaterCannonRoof btnCannonRoof = new ButtonRotaryWaterCannonRoof(this.mixingProcessor) {
                @Override
                public void operateDevice() {
                    ((MixingProcessor) this.operatingDevice).setSprayCapacityPerlIteration(CannonIdentifier.CANNON_ROOF, this.amountPerIteration);
                }
            };

            Joystick joystickDriver = buildJoystick(true);
            Joystick joystickOperator = buildJoystick(false);

            SteeringWheel steeringWheel = new SteeringWheel(centralUnit) {
                @Override
                public void operateDevice() {
                    ((CentralUnit) this.operatingDevice).steer(this.getDegree());
                }
            };

            this.cabin = new Cabin.Builder(
                    this.buildControlPanel(centralUnit),
                    pedalAcc,
                    pedalBrake,
                    btnCannonRoof,
                    btnCannonFront,
                    joystickDriver,
                    joystickOperator,
                    steeringWheel,
                    centralUnit
            ).build();


        }

        private void buildLights() {
            // add Breaklights
            for (int i = 0; i < 2; i++) {
                LightPosition position = i == 0 ? LightPosition.BACK_LEFT : LightPosition.BACK_RIGHT;
                this.brakingLights.add(new BrakingLight(position));
            }

            // add Searchlights Front
            for (int i = 0; i < 6; i++) {
                LightPosition position = switch (i) {
                    case 0, 1, 2 -> LightPosition.FRONT_LEFT;
                    default -> LightPosition.FRONT_RIGHT;
                };
                this.searchLightsFront.add(new SearchLight(position));
            }
            for (int i = 0; i < 4; i++) {
                this.searchLightsRoof.add(new SearchLight(LightPosition.ROOF_FRONT));
            }
            for (int i = 0; i < 10; i++) {
                LightPosition position = i < 5 ? LightPosition.LEFT_SIDE : LightPosition.RIGHT_SIDE;
                this.searchLightsSide.add(new SearchLight((position)));
            }

            // add Indicators
            for (int i = 0; i < 4; i++) {
                LightPosition position = switch (i) {
                    case 0 -> LightPosition.FRONT_LEFT;
                    case 1 -> LightPosition.BACK_LEFT;
                    case 2 -> LightPosition.FRONT_RIGHT;
                    default -> LightPosition.BACK_RIGHT;
                };
                if (i < 2) {
                    this.directionIndicatorsLeft.add(new DirectionIndicator(position));
                } else {
                    this.directionIndicatorsRight.add(new DirectionIndicator(position));
                }

            }

            // add small FlashingBlueLights
            for (int i = 0; i < 2; i++) {
                this.flashingBlueLights.add(new FlashingBlueLightSmall(LightPosition.FRONT_AREA));
            }

            // add medium FlashingBlueLights
            for (int i = 0; i < 4; i++) {
                LightPosition position = i < 2 ? LightPosition.ROOF_BACK_LEFT : LightPosition.ROOF_BACK_RIGHT;
                this.flashingBlueLights.add(new FlashingBlueLightMedium(position));
            }

            // add big FlashingBlueLights
            for (int i = 0; i < 4; i++) {
                LightPosition position = i < 2 ? LightPosition.ROOF_FRONT_LEFT : LightPosition.ROOF_FRONT_RIGHT;
                this.flashingBlueLights.add(new FlashingBlueLightBig(position));
            }

            // add WarningLights
            for(int i = 0; i < 2; i++){
                this.warningLights.add(new WarningLight(i<1?LightPosition.ROOF_FRONT_LEFT:LightPosition.ROOF_BACK_RIGHT));
            }
        }

        private Joystick buildJoystick(Boolean isDriver) {
            ButtonPress btnPressLeft;
            ButtonPress btnPressRight;
            ButtonPush btnPush;
            CannonIdentifier ident = isDriver ? CannonIdentifier.CANNON_FRONT : CannonIdentifier.CANNON_ROOF;

            btnPressLeft = new ButtonPress(this.mixingProcessor) {
                @Override
                public void operateDevice() {
                    ((MixingProcessor) this.operatingDevice).toggle(ident);
                }
            };
            btnPressRight = new ButtonPress(this.mixingProcessor) {
                @Override
                public void operateDevice() {
                    if (((MixingProcessor) this.operatingDevice).getCannonState(ident)) {
                        ((MixingProcessor) this.operatingDevice).changeMixingRate();
                    }
                }
            };
            btnPush = new ButtonPush(this.mixingProcessor) {
                @Override
                public void operateDevice() {
                    if (((MixingProcessor) this.operatingDevice).getCannonState(ident)) {
                        ((MixingProcessor) this.operatingDevice).spray(ident);
                    }
                }
            };

            return new Joystick(btnPush, btnPressLeft, btnPressRight);
        }

        private ControlPanel buildControlPanel(CentralUnit cu) {
            List<ButtonSwitch> switches = new ArrayList<>();
            ButtonSwitch btnWarnLight = new ButtonSwitch(cu) {
                @Override
                public void operateDevice() {
                    ((CentralUnit) this.operatingDevice).switchWarningLight();
                }
            };

            ButtonSwitch btnBlueLight = new ButtonSwitch(cu) {
                @Override
                public void operateDevice() {
                    ((CentralUnit) this.operatingDevice).switchBlueLight();
                }
            };

            ButtonSwitch btnFrontLight = new ButtonSwitch(cu) {
                @Override
                public void operateDevice() {
                    ((CentralUnit) this.operatingDevice).switchFrontLight();
                }
            };

            ButtonSwitch btnRoofLight = new ButtonSwitch(cu) {
                @Override
                public void operateDevice() {
                    ((CentralUnit) this.operatingDevice).switchRoofLight();
                }
            };

            ButtonSwitch btnSideLight = new ButtonSwitch(cu) {
                @Override
                public void operateDevice() {
                    ((CentralUnit) this.operatingDevice).switchSideLight();
                }
            };

            ButtonSwitch btnSelfProtection = new ButtonSwitch(cu) {
                @Override
                public void operateDevice() {
                    ((CentralUnit) this.operatingDevice).switchSelfprotection();
                }
            };

            ButtonSwitch btnEngines = new ButtonSwitch(cu) {
                @Override
                public void operateDevice() {
                    ((CentralUnit) this.operatingDevice).switchEngines();
                }
            };

            return new ControlPanel.Builder(btnEngines, btnWarnLight, btnBlueLight, btnFrontLight, btnRoofLight, btnSideLight, btnSelfProtection).build();
        }

        /**
         * @return FLF.FLF
         */
        public FLF build() {
            return new FLF(this);
        }


    }
}