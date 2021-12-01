package StoreTesting;

import BatteryManagement.Battery;
import BatteryManagement.BatteryUnit;
import Tank.Tank;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StoreTestingBattery {
    @Test
    public void testFill() {
        Integer length = 10, height = 10, width = 10, quantity = 25;
        Battery battery = new Battery(BatteryUnit.POSITIVE, length, height, width);

        battery.fill(BatteryUnit.POSITIVE, quantity);
        assertEquals(battery.getRelativeFillState(), 1 / ((length * height * width) / quantity.doubleValue()));
    }

    @Test
    public void testRemove() {
        Integer length = 10, height = 10, width = 10, quantity = 25, remove=15;
        Battery battery = new Battery(BatteryUnit.POSITIVE, length, height, width);


        battery.fill(BatteryUnit.POSITIVE, quantity);
        Enum output[] = battery.remove(remove);
        assertEquals(battery.getRelativeFillState(), 1 / ((length * height * width) / (quantity.doubleValue()-remove)));
        assertEquals(output[0], BatteryUnit.POSITIVE);
    }
}
