package Store;

import Tank.TankSubject;

public interface IStoreMedium {
    void fill(Enum input, Integer quantity);
    Enum[] remove(Integer quantity);
    Integer getRelativeFillState();
}
