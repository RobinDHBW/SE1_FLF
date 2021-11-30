package Tank;

import Store.StoreMedium;

import java.util.Arrays;
import java.util.HashMap;

public class Tank extends StoreMedium {
    TankSubject subject;
    HashMap<Character, Integer> fillState = new HashMap<>();
    Boolean isFull = false;
    Boolean isEmpty = true;

    public Tank(TankSubject subject, Integer length, Integer height, Integer width) {
        super(length, height, width);
        this.subject = subject;
    }

    @Override
    public void fill(Object input, Integer quantity) {
        //super.fill(input, quantity);
    }

    @Override
    public TankSubject[] remove(Integer quantity) {
        return (TankSubject[]) super.remove(quantity);
    }

    public HashMap<Character, Integer> getFillState() {
        return fillState;
    }

    public TankSubject getSubject() {
        return subject;
    }

    public Integer getRelativeFillState() {
        return (store.length * store[0].length * store[0][0].length) / (fillState.get('x') * fillState.get('y') * fillState.get('z'));
    }
}
