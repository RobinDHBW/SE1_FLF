package Store;

import Tank.TankSubject;

public interface IStoreMedium {
    void fill(Object input, Integer quantity);
    Object[] remove(Integer quantity);
    Double getRelativeFillState();
}
