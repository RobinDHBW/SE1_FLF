package BatteryManagement;

import Store.StoreMedium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<Object> remove(Integer quantity) {
        return super.remove(quantity);
    }
}
