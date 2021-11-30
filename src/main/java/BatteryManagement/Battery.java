package BatteryManagement;

import Store.StoreMedium;

import java.util.HashMap;

public class Battery extends StoreMedium {

    Boolean[][][] battery;
    HashMap<Character, Integer> fillState = new HashMap<>();
    Boolean isFull = false;
    Boolean isEmpty = true;

    public Battery(Integer length, Integer height, Integer width){
        super(length, height, width);
    }

    @Override
    public void fill(Object input, Integer quantity) {

    }

    @Override
    public Object[] remove(Integer quantity) {
        return super.remove(quantity);
    }
}
