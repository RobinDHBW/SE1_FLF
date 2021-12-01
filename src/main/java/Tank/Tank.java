package Tank;

import Store.StoreMedium;

import java.util.HashMap;

public class Tank extends StoreMedium {
    HashMap<Character, Integer> fillState = new HashMap<>();
    Boolean isFull = false;
    Boolean isEmpty = true;

    public Tank(TankSubject subject, Integer length, Integer height, Integer width) {
        super(length, height, width, subject);
    }

    @Override
    public void fill(Enum input, Integer quantity){
        super.fill(input,quantity);
    };

    @Override
    public Enum[] remove(Integer quantity) {
        return super.remove(quantity);
    }

    public HashMap<Character, Integer> getFillState() {
        return fillState;
    }


}
