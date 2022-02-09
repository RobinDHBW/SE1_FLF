package BatteryManagement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BatteryBox {
    private final ArrayList<Battery> batteryStore = new ArrayList<>();
    Integer width, height;

    public BatteryBox(Integer width, Integer height) {
        this.width = width;
        this.height = height;

        for (int i = 0; i < width * height; i++) {
            batteryStore.add(new Battery(new Coulomb(), 100, 10, 100));
        }
    }

    public void fill(Integer quantity) {
        for (Battery b : batteryStore) {
            b.fill(new Coulomb(), quantity / batteryStore.size());
        }
    }

    public List<Coulomb> remove(Integer quantity) {
            List<Coulomb> output = new ArrayList<>();
            for (Battery b : batteryStore) {
                output = Stream.concat(output.stream(), b.remove(quantity / batteryStore.size()).stream().map(e -> (Coulomb) e).collect(Collectors.toList()).stream()).collect(Collectors.toList());
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
