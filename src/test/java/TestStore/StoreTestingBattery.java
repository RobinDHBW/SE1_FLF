package TestStore;

import BatteryManagement.Battery;
import BatteryManagement.Coulomb;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StoreTestingBattery {
    @Test
    public void testFill() {
        Integer length = 10, height = 10, width = 10, quantity = 25;
        Battery battery = new Battery(new Coulomb(), length, height, width);

        battery.fill(new Coulomb(), quantity);
        assertEquals(battery.getRelativeFillState(), 1 / ((length * height * width) / quantity.doubleValue()));
    }

    @Test
    public void testRemove() {
        Integer length = 10, height = 10, width = 10, quantity = 25, remove = 15;
        Battery battery = new Battery(new Coulomb(), length, height, width);


        battery.fill(new Coulomb(), quantity);
        List<Object> output = battery.remove(remove);
        assertEquals(battery.getRelativeFillState(), 1 / ((length * height * width) / (quantity.doubleValue() - remove)));
        assertTrue(output.get(0).getClass().equals(new Coulomb().getClass()));
    }
}
