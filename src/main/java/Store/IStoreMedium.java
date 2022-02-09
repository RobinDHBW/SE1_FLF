package Store;

import java.util.List;

public interface IStoreMedium {
    void fill(Object input, Double quantity);
    List<Object> remove(Double quantity);
    Double getRelativeFillState();
}
