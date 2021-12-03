package FLF;

import Button.*;
import Cabin.*;
import Drive.Drive;
import Firefighting.WaterCannonFront;
import Firefighting.WaterCannonRoof;
import Firefighting.WaterDieSelfprotection;
import Instruments.SteeringWheel;
import Joystick.Joystick;
import Lights.*;
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
    private final List<DirectionIndicator> directionIndicators;
    private final List<BrakingLight> brakingLights;

    private final List<FlashingBlueLight> flashingBlueLights;
    private final List<WarningLight> warningLights;

    private final Cabin cabin;

    private final Drive drive;

    private final WaterCannonRoof waterCannonRoof;
    private final WaterCannonFront waterCannonFront;
    private final List<WaterDieSelfprotection> waterDiesSelfprotection;

    private final MixingProcessor mixingProcessor;
    //private final BatteryManagement batteryManagement;


    private FLF(Builder builder) {

        FLF built = builder.build();
        this.brakingLights = built.brakingLights;
        this.searchLightsFront = built.searchLightsFront;
        this.searchLightsRoof = built.searchLightsRoof;
        this.searchLightsSide = built.searchLightsSide;
        this.directionIndicators = built.directionIndicators;

        this.flashingBlueLights = built.flashingBlueLights;
        this.warningLights = built.warningLights;

        this.cabin = built.cabin;

        this.drive = built.drive;

        this.waterCannonRoof = built.waterCannonRoof;
        this.waterCannonFront = built.waterCannonFront;
        this.waterDiesSelfprotection = built.waterDiesSelfprotection;

        this.mixingProcessor = built.mixingProcessor;
        //this.batteryManagement = built.batteryManagement;
    }

    /**
     *
     */
    public static class Builder {

        private final static List<WarningLight> warningLights = new ArrayList<WarningLight>();
        private final List<DirectionIndicator> directionIndicators = new ArrayList<DirectionIndicator>();
        private final List<BrakingLight> brakingLights = new ArrayList<BrakingLight>();
        private final List<SearchLight> searchLightsFront = new ArrayList<SearchLight>();
        private final List<SearchLight> searchLightsRoof = new ArrayList<SearchLight>();
        private final List<SearchLight> searchLightsSide = new ArrayList<SearchLight>();
        private final List<FlashingBlueLight> flashingBlueLights = new ArrayList<FlashingBlueLight>();
        private final Cabin cabin;

        private final Drive drive = new Drive();

        private final WaterCannonRoof waterCannonRoof = new WaterCannonRoof(500);
        private final WaterCannonFront waterCannonFront = new WaterCannonFront(500, 90);
        private final List<WaterDieSelfprotection> waterDiesSelfprotection = new ArrayList<WaterDieSelfprotection>();

        private final MixingProcessor mixingProcessor = new MixingProcessor();
        //private final BatteryManagement batteryManagement = BatteryManagement;

        public Builder() {

            CentralUnit centralUnit = new CentralUnit(warningLights, flashingBlueLights, searchLightsFront, searchLightsRoof, searchLightsSide, waterDiesSelfprotection, drive);

            buildLights();
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

            ButtonRotaryWaterCannonFront btnCannonFront = new ButtonRotaryWaterCannonFront(this.waterCannonFront) {
                @Override
                public void operateDevice() {
                    ((WaterCannonFront) this.operatingDevice).setSprayCapacityPerlIteration(this.amountPerIteration);
                }
            };
            ButtonRotaryWaterCannonRoof btnCannonRoof = new ButtonRotaryWaterCannonRoof(this.waterCannonRoof) {
                @Override
                public void operateDevice() {
                    ((WaterCannonRoof) this.operatingDevice).setSprayCapacityPerlIteration(this.amountPerIteration);
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
                    this.buildControlPanelButtons(centralUnit),
                    pedalAcc,
                    pedalBrake,
                    btnCannonRoof,
                    btnCannonFront,
                    joystickDriver,
                    joystickOperator,
                    steeringWheel,
                    centralUnit
            ).build();


            //add Waterdies
            for (int i = 0; i < 7; i++) {
                this.waterDiesSelfprotection.add(new WaterDieSelfprotection(100));
            }
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
                    case 1 -> LightPosition.FRONT_RIGHT;
                    case 2 -> LightPosition.BACK_LEFT;
                    default -> LightPosition.BACK_RIGHT;
                };
                this.directionIndicators.add(new DirectionIndicator(position));
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
            this.warningLights.add(new WarningLight(LightPosition.ROOF_FRONT_LEFT));
            this.warningLights.add(new WarningLight(LightPosition.ROOF_BACK_RIGHT));
        }

        private Joystick buildJoystick(Boolean isDriver) {
            ButtonPress btnPressLeft;
            ButtonPress btnPressRight;
            ButtonPush btnPush;
            if (isDriver) {
                btnPressLeft = new ButtonPress(this.waterCannonFront) {
                    @Override
                    public void operateDevice() {
                        ((WaterCannonFront) this.operatingDevice).toggle();
                    }
                };
                btnPressRight = new ButtonPress(this.mixingProcessor) {
                    @Override
                    public void operateDevice() {
                        ((MixingProcessor) this.operatingDevice).changeMixingRate();
                    }
                };
                btnPush = new ButtonPush(this.waterCannonFront) {
                    @Override
                    public void operateDevice() {
                        ((WaterCannonFront) this.operatingDevice).spray(new TankSubject[0]); //@TODO TankSubject da rein bringen^^
                    }
                };
            } else {
                btnPressLeft = new ButtonPress(this.waterCannonRoof) {
                    @Override
                    public void operateDevice() {
                        ((WaterCannonRoof) this.operatingDevice).toggle();
                    }
                };
                btnPressRight = new ButtonPress(this.mixingProcessor) {
                    @Override
                    public void operateDevice() {
                        ((MixingProcessor) this.operatingDevice).changeMixingRate();
                    }
                };
                btnPush = new ButtonPush(this.waterCannonRoof) {
                    @Override
                    public void operateDevice() {
                        ((WaterCannonRoof) this.operatingDevice).spray(new TankSubject[0]); //@TODO TankSubject da rein bringen^^
                    }
                };
            }
            return new Joystick(btnPush, btnPressLeft, btnPressRight);
        }

        private List<ButtonSwitch> buildControlPanelButtons(CentralUnit cu) {
            List<ButtonSwitch> switches = new ArrayList<ButtonSwitch>();
            switches.add(new ButtonSwitch(cu) {
                @Override
                public void operateDevice() {
                    ((CentralUnit) this.operatingDevice).switchWarningLight();
                }
            });

            switches.add(new ButtonSwitch(cu) {
                @Override
                public void operateDevice() {
                    ((CentralUnit) this.operatingDevice).switchBlueLight();
                }
            });

            switches.add(new ButtonSwitch(cu) {
                @Override
                public void operateDevice() {
                    ((CentralUnit) this.operatingDevice).switchFrontLight();
                }
            });

            switches.add(new ButtonSwitch(cu) {
                @Override
                public void operateDevice() {
                    ((CentralUnit) this.operatingDevice).switchRoofLight();
                }
            });

            switches.add(new ButtonSwitch(cu) {
                @Override
                public void operateDevice() {
                    ((CentralUnit) this.operatingDevice).switchSideLight();
                }
            });

            switches.add(new ButtonSwitch(cu) {
                @Override
                public void operateDevice() {
                    ((CentralUnit) this.operatingDevice).switchSelfprotection();
                }
            });

            switches.add(new ButtonSwitch(cu) {
                @Override
                public void operateDevice() {
                    ((CentralUnit) this.operatingDevice).switchEngines();
                }
            });


            return switches;
        }

        /**
         * @return FLF.FLF
         */
        public FLF build() {
            return new FLF(this);
        }
    }
}