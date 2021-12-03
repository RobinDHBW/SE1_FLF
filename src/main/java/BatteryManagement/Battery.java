package BatteryManagement;

import Store.StoreMedium;

import java.util.HashMap;

public class Battery extends StoreMedium {

    protected BatteryUnit[][][] battery;
    protected HashMap<Character, Integer> fillState = new HashMap<>();

    public Battery(Object subject, Integer length, Integer height, Integer width) {
        super(length, height, width, subject);
    }

    @Override
    public void fill(Object input, Integer quantity) {
        super.fill(input, quantity);
    }

    @Override
    public Object[] remove(Integer quantity) {
        return super.remove(quantity);
    }
}
