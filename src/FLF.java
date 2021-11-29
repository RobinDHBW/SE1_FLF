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

            for (int i = 0; i < 4; i++) {
                seatList.add(new Seat());
            }


        }

        /**
         * @return FLF
         */
        public FLF build() {
            return new FLF(this);
        }

        ;
    }
}