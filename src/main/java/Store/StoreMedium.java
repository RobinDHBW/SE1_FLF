package Store;

import Tank.TankSubject;

import java.util.HashMap;

public abstract class StoreMedium implements IStoreMedium {
    protected Enum[][][] store;
    protected HashMap<Character, Integer> fillState = new HashMap<>();
    protected Boolean isFull = false;
    protected Boolean isEmpty = true;
    protected Enum subject;

    public StoreMedium(Integer length, Integer height, Integer width, Enum subject) {

        this.store = new Enum[length][height][width];
        this.subject = subject;

        fillState.put('x', length);
        fillState.put('y', height);
        fillState.put('z', width);
    }

    /**
     *
     * @param input
     * @param quantity
     */
    public void fill(Enum input, Integer quantity) {
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
    public Enum[] remove(Integer quantity) {
        int x = fillState.get('x');
        int y = fillState.get('y');
        int z = fillState.get('z');

        Enum[] output = new Enum[quantity];

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

    public Integer getRelativeFillState() {
        return (store.length * store[0].length * store[0][0].length) / (fillState.get('x') * fillState.get('y') * fillState.get('z'));
    }

    public Enum getSubject() {
        return subject;
    }
}
