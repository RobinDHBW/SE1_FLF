package BatteryManagement;

import Tank.TankSubject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

public class BatteryBox {
    Integer width, height;
    private ArrayList<Battery> batteryStore = new ArrayList<>();

    public BatteryBox(Integer width, Integer height) {
        this.width = width;
        this.height = height;

        for (int i = 0; i < width * height; i++) {
            batteryStore.add(new Battery(BatteryUnit.POSITIVE, 100, 10, 100));
        }
    }

    public void fill(Integer quantity) {
        for (Battery b : batteryStore) {
            b.fill(BatteryUnit.POSITIVE, quantity / 4);
        }
    }

    public Enum[] remove(Integer quantity) {
        Enum[] output = new Enum[0];
        for (Battery b : batteryStore) {
            output = Stream.concat(Arrays.stream(b.remove(quantity / 4)), Arrays.stream(output)).toArray(size -> (Enum[]) Array.newInstance(Enum.class, size));
        }
        return output;
    }

    public Double getRelativeFillState() {
        return batteryStore.stream()
                .mapToDouble(x -> x.getRelativeFillState())
                .average()
                .orElse(0);
    }

    public Integer getCapacity() {
        return batteryStore.stream()
                .mapToInt(x -> x.getCapacity())
                .sum();
    }
}
