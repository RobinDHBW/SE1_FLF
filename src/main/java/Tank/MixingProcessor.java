package Tank;

import java.lang.reflect.Array;
import java.util.Arrays;
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

    public TankSubject[] mix(Integer quantity) {

        Integer foamPortion = switch (this.mixingRate) {
            case NULL -> 0;
            case THREE -> (quantity / 100) * 3;
            case FIVE -> (quantity / 100) * 5;
            case TEN -> (quantity / 100) * 10;
        };

        return Stream.concat(Arrays.stream(foamTank.remove(foamPortion)), Arrays.stream(waterTank.remove(quantity - foamPortion)))
                .toArray(size -> (TankSubject[]) Array.newInstance(FOAM.getDeclaringClass().componentType(), size));

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
