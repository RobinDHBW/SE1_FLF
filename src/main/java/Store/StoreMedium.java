package Store;

import Tank.TankSubject;

import java.util.HashMap;

public abstract class StoreMedium implements IStoreMedium {
    protected Object[][][] store;
    protected HashMap<Character, Integer> fillState = new HashMap<>();
    protected Boolean isFull = false;
    protected Boolean isEmpty = true;

    public StoreMedium(Integer length, Integer height, Integer width) {

        this.store = new Object[length][height][width];

        fillState.put('x', length);
        fillState.put('y', height);
        fillState.put('z', width);
    }

    /**
     *
     * @param input
     * @param quantity
     */
    public void fill(Object input, Integer quantity) {
        int x = fillState.get('x');
        int y = fillState.get('y');
        int z = fillState.get('z');

        if (!isFull) {
            for (int j = y; j > 0; j--) {
                for (int i = x; i > 0; i--) {
                    for (int k = z; k > 0; k--) {
                        if (j == 1 && i == 1 && k == 1) isFull = true;
                        if (quantity-- == 0) break;
                        this.store[i][j][k] = input;
                        fillState.put('x', i);
                        fillState.put('y', j);
                        fillState.put('z', k);
                    }
                }
            }
        }
    }

    /**
     *
     * @param quantity
     * @return
     */
    public Object[] remove(Integer quantity) {
        int x = fillState.get('x');
        int y = fillState.get('y');
        int z = fillState.get('z');

        Object[] output = new Object[quantity];

        if (!isEmpty) {
            for (int j = y; j < store[0].length; j++) {
                for (int i = x; i < store.length; i++) {
                    for (int k = z; k < store[0][0].length; k++) {
                        if (j == store[0].length && i == store.length && k == store[0][0].length) isFull = true;
                        if (quantity-- == 0) break;
                        output[quantity] = store[i][j][k];
                        store[i][j][k] = null;
                    }
                }
            }
        }
        return output;
    }
}
