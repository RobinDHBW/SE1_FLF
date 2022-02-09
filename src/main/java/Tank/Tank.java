package Tank;

import Store.StoreMedium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Tank extends StoreMedium {

    public Tank(TankSubject subject, Integer length, Integer height, Integer width) {
        super(length, height, width, subject);
    }

    @Override
    public void fill(Object input, Double quantity) {
        super.fill(input, quantity);
    }

    @Override
    public List<Object> remove(Double quantity) {
        return super.remove(quantity);
    }

    public Double getFillState() {
        return getRelativeFillState();
    }


}
