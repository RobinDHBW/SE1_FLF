package Tank;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.stream.Stream;

public class MixingProcessor {
    private MixingRate mixingRate;

    private Tank foamTank = new Tank(TankSubject.FOAM, 25, 10, 10);
    private Tank waterTank = new Tank(TankSubject.WATER, 50, 25, 10);

    public MixingProcessor() {
        this.setMixingRate(MixingRate.NULL);
    }

    public void setMixingRate(MixingRate mixingRate) {
        this.mixingRate = mixingRate;
    }

    public TankSubject[] mix(Integer quantity) {

        Integer foamPortion = switch (this.mixingRate) {
            case NULL -> 0;
            case THREE -> (quantity / 100) * 3;
            case FIVE -> (quantity / 100) * 5;
            case TEN -> (quantity / 100) * 10;
        };

        return Stream.concat(Arrays.stream(foamTank.remove(foamPortion)), Arrays.stream(waterTank.remove(quantity - foamPortion)))
                .toArray(size -> (TankSubject[]) Array.newInstance(TankSubject.FOAM.getDeclaringClass().componentType(), size));

    }

}
