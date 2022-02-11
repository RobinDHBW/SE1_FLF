package Tank;

import BatteryManagement.Coulomb;
import Firefighting.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static Tank.TankSubject.FOAM;

public class MixingProcessor {
    private final WaterCannonRoof waterCannonRoof = new WaterCannonRoof();
    private final WaterCannonFront waterCannonFront = new WaterCannonFront(90);
    private final List<WaterDieSelfprotection> waterDiesSelfprotection = new ArrayList<>();
    private MixingRate mixingRate = MixingRate.NULL;
    private final Tank foamTank = new Tank(FOAM, 75, 45, 10);
    private final Tank waterTank = new Tank(TankSubject.WATER, 75, 45, 30);

    public MixingProcessor() {
        //add Waterdies
        for (int i = 0; i < 7; i++) {
            this.waterDiesSelfprotection.add(new WaterDieSelfprotection(100));
        }
    }

    private Integer calcFoamPortion(Integer quantity){
        return switch (this.mixingRate) {
            case NULL -> 0;
            case THREE -> (quantity / 100) * 3;
            case FIVE -> (quantity / 100) * 5;
            case TEN -> (quantity / 100) * 10;
        };
    }

    private List<TankSubject> mix(Integer quantity) {
        Integer foamPortion = calcFoamPortion(quantity);

        return Stream.concat(
                foamTank.remove(foamPortion).stream().map(e -> (TankSubject) e).collect(Collectors.toList()).stream(),
                waterTank.remove(quantity - foamPortion).stream().map(e -> (TankSubject) e)
                        .collect(Collectors.toList()).stream()).collect(Collectors.toList());

    }

    public void changeMixingRate() {
        this.mixingRate = switch (this.mixingRate) {
            case NULL -> MixingRate.THREE;
            case THREE -> MixingRate.FIVE;
            case FIVE -> MixingRate.TEN;
            default -> MixingRate.NULL;
        };

    }

    public void fill(Enum input, Integer quantity) {

        if (input.equals(FOAM)) {
            foamTank.fill(input, quantity);
        } else {
            waterTank.fill(input, quantity);
        }
    }

    public void fillComplete(Enum input) {
        Integer toFill = 0;
        Integer actualFillState = input.equals(FOAM) ? foamTank.getAbsoluteFillState() :  waterTank.getAbsoluteFillState();

        if (input.equals(FOAM)) {
            toFill = foamTank.getCapacity() - actualFillState;
        } else {
            toFill = waterTank.getCapacity() - actualFillState;
        }

        this.fill(input, toFill);
    }

    public void toggle(CannonIdentifier ident) {
        switch (ident) {
            case CANNON_ROOF -> this.waterCannonRoof.toggle();
            case CANNON_FRONT -> this.waterCannonFront.toggle();
            case CANNON_SELFPROTECTION -> {
                for (WaterDieSelfprotection die : this.waterDiesSelfprotection) {
                    die.toggle();
                }
            }
        }
    }

    public void setSprayCapacityPerlIteration(CannonIdentifier ident, Integer amount) {
        switch (ident) {
            case CANNON_ROOF -> this.waterCannonRoof.setSprayCapacityPerlIteration(amount);
            case CANNON_FRONT -> this.waterCannonFront.setSprayCapacityPerlIteration(amount);
        }
    }

    /**
     * @param identifier
     */
    public void spray(CannonIdentifier identifier) {
        switch (identifier) {
            case CANNON_FRONT -> this.waterCannonFront.spray(this.mix(this.waterCannonFront.getSprayCapacityPerlIteration()));
            case CANNON_ROOF -> this.waterCannonRoof.spray(this.mix(this.waterCannonRoof.getSprayCapacityPerlIteration()));
            case CANNON_SELFPROTECTION -> {
                for (WaterDieSelfprotection die : this.waterDiesSelfprotection) {
                    die.spray(this.waterTank.remove(this.waterDiesSelfprotection.get(0).getSprayCapacityPerlIteration()).stream().map(e -> (TankSubject) e).collect(Collectors.toList()));
                }
            }
        }
    }

    public Boolean getCannonState(CannonIdentifier ident) {
        return switch (ident) {
            case CANNON_ROOF -> this.waterCannonRoof.getState();
            case CANNON_FRONT -> this.waterCannonFront.getState();
            case CANNON_SELFPROTECTION -> this.waterDiesSelfprotection.get(0).getState();
        };
    }

    public MixingRate getMixingRate() {
        return mixingRate;
    }
    public Integer getMixingRateValue() {
        return calcFoamPortion(1);
    }

    public Double getTankFillState(TankSubject ts){
       return switch (ts){
            case FOAM -> this.foamTank.getRelativeFillState();
            case WATER -> this.waterTank.getRelativeFillState();
        };
    }

    public Integer getAbsoluteFillState(TankSubject ts){
        return switch (ts){
            case FOAM -> this.foamTank.getAbsoluteFillState();
            case WATER -> this.waterTank.getAbsoluteFillState();
        };
    }

    public Integer getCapacity(TankSubject ts){
        return switch (ts){
            case FOAM -> this.foamTank.getCapacity();
            case WATER -> this.waterTank.getCapacity();
        };
    }

    public Integer getSprayCapacity(CannonIdentifier ci){
        return switch (ci){
            case CANNON_FRONT -> this.waterCannonFront.getSprayCapacityPerlIteration();
            case CANNON_ROOF -> this.waterCannonRoof.getSprayCapacityPerlIteration();
            case CANNON_SELFPROTECTION -> this.waterDiesSelfprotection.get(0).getSprayCapacityPerlIteration();
        };
    }
}
