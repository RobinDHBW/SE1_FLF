package BatteryManagement;

import java.util.ArrayList;

public class Box {
    Integer width, height;
    private ArrayList<Battery> batteryStore = new ArrayList<>();

    public Box(Integer width, Integer height) {
        this.width = width;
        this.height = height;

        for (int i = 0; i < width * height; i++) {
            batteryStore.add(new Battery(BatteryUnit.POSITIVE, 100, 10, 100));
        }
    }

    public Integer takeout(int amount) {
        return amount;

    }

    public Double getRelativeFillState() {
        return batteryStore.stream()
                .mapToDouble(x -> x.getRelativeFillState())
                .average()
                .orElse(0);
    }
}
