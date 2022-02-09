package TestStore;


import Tank.Tank;
import Tank.TankSubject;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class StoreTestingTank {
    @Test
    public void testFill() {
        Integer length = 10, height = 10, width = 10;
        Double quantity = 25.0;
        Tank tank = new Tank(TankSubject.FOAM, length, height, width);

        tank.fill(TankSubject.FOAM, quantity);
        assertEquals(tank.getRelativeFillState(), 1 / ((length * height * width) / quantity.doubleValue()));
    }

    @Test
    public void testRemove() {
        Integer length = 10, height = 10, width = 10;
        Double quantity = 25.0, remove = 15.0;
        Tank tank = new Tank(TankSubject.FOAM, length, height, width);


        tank.fill(TankSubject.FOAM, quantity);
        List<Object> output = tank.remove(remove);
        assertEquals(tank.getRelativeFillState(), 1 / ((length * height * width) / (quantity.doubleValue()-remove)));
        assertEquals(output.get(0), TankSubject.FOAM);
    }

}
