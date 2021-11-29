import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class FLF {
    private final List<Seat> seatList;

    private FLF(Builder builder) {
        seatList = builder.build().seatList;
    }

    /**
     *
     */
    public static class Builder {
        private final List<Seat> seatList = new ArrayList<>();

        public Builder() {

            for (int i = 0; i < 2; i++) {
                boolean leftSide = i == 0 ;
                seatList.add(new Seat(1, leftSide, false));
                seatList.add(new SeatFirefighting(leftSide, false));
            }


        }

        /**
         * @return FLF
         */
        public FLF build() {
            return new FLF(this);
        }
    }
}