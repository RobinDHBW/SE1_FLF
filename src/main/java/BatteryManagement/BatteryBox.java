package BatteryManagement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BatteryBox {
    private final ArrayList<Battery> batteryStore = new ArrayList<>();
    private final Integer width, height;

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
            if(quantity == 0) break;
            Integer fillState = b.getAbsoluteFillState();
            Integer toRemove = quantity;
            if (quantity > fillState) toRemove = fillState;

            output.addAll(b.remove(toRemove).stream().map(x -> (Coulomb) x).collect(Collectors.toList()));
            quantity -= toRemove;
        }
        return output;
    }

    public Double getRelativeFillState() {
        return batteryStore.stream()
                .mapToDouble(x -> x.getRelativeFillState())
                .average()
                .orElse(0);
    }

    public Integer getAbsoluteFillState() {
        return batteryStore.stream()
                .mapToInt(x -> x.getAbsoluteFillState())
                .sum();
    }

    public Integer getCapacity() {
        return batteryStore.stream()
                .mapToInt(x -> x.getCapacity())
                .sum();
    }
}
