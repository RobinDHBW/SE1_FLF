package Tank;

import BatteryManagement.Coulomb;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static Tank.TankSubject.FOAM;

public class MixingProcessor {
    private MixingRate mixingRate = MixingRate.NULL;

    private Tank foamTank = new Tank(FOAM, 25, 10, 10);
    private Tank waterTank = new Tank(TankSubject.WATER, 50, 25, 10);

    public MixingProcessor() {

    }

    public void changeMixingRate() {
        this.mixingRate = switch (this.mixingRate) {
            case NULL -> MixingRate.THREE;
            case THREE -> MixingRate.FIVE;
            case FIVE -> MixingRate.TEN;
            default -> MixingRate.NULL;
        };

    }

    public List<TankSubject> mix(Integer quantity) {

        Integer foamPortion = switch (this.mixingRate) {
            case NULL -> 0;
            case THREE -> (quantity / 100) * 3;
            case FIVE -> (quantity / 100) * 5;
            case TEN -> (quantity / 100) * 10;
        };

        return Stream.concat(
                foamTank.remove(foamPortion).stream().map(e -> (TankSubject) e).collect(Collectors.toList()).stream(),
                waterTank.remove(quantity - foamPortion).stream().map(e -> (TankSubject) e)
                        .collect(Collectors.toList()).stream()).collect(Collectors.toList());

    }

    public void fill(Enum input, Integer quantity) {

        if (input.equals(FOAM)) {
            foamTank.fill(input, quantity);
        } else {
            waterTank.fill(input, quantity);
        }
    }

    public void fillComplete(Enum input, Integer quantity) {
        Integer toFill = 0;
        Double actualFillState;

        if (input.equals(FOAM)) {
            actualFillState = foamTank.getCapacity() * foamTank.getRelativeFillState();
            toFill = foamTank.getCapacity() - actualFillState.intValue();
        } else {
            actualFillState = waterTank.getCapacity() * waterTank.getRelativeFillState();
            toFill = waterTank.getCapacity() - actualFillState.intValue();
        }

        this.fill(input, toFill);
    }

}
