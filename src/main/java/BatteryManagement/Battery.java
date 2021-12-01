package BatteryManagement;

import Store.StoreMedium;

import java.util.HashMap;

public class Battery extends StoreMedium {

    BatteryUnit[][][] battery;
    HashMap<Character, Integer> fillState = new HashMap<>();
    Boolean isFull = false;
    Boolean isEmpty = true;

    public Battery(BatteryUnit subject, Integer length, Integer height, Integer width) {

        super(length, height, width, subject);
    }

    @Override
    public void fill(Enum input, Integer quantity) {
        super.fill(input, quantity);
    }

    @Override
    public Enum[] remove(Integer quantity) {
        return super.remove(quantity);
    }
}
