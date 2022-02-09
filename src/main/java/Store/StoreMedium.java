package Store;

import java.util.*;
import java.util.stream.Collectors;

public abstract class StoreMedium implements IStoreMedium {
    protected Object[][][] store;
    protected HashMap<Character, Integer> fillState = new HashMap<>();
    protected Boolean isFull = false;
    protected Boolean isEmpty = true;
    protected Object subject;
    protected Integer capacity;

    public StoreMedium(Integer length, Integer height, Integer width, Object subject) {

        this.store = new Object[length][height][width];
        this.subject = subject;
        this.capacity = length * height * width;

        fillState.put('x', length);
        fillState.put('y', height);
        fillState.put('z', width);
    }

    protected void fillLoop(Object input, Integer quantity) {
        int x = fillState.get('x');
        int y = fillState.get('y');
        int z = fillState.get('z');

        /**
         * Count backwards from full to 0
         * x == max length -> to count to 0, we need to start with x-1
         */
        for (int i = x - 1; i >= 0; i--) {
            for (int j = y - 1; j >= 0; j--) {
                for (int k = z - 1; k >= 0; k--) {
                    if (j == 1 && i == 1 && k == 1) isFull = true;
                    if (quantity-- == 0) return;
                    this.store[i][j][k] = input;
                    isEmpty = false;
                    fillState.put('x', i);
                    fillState.put('y', j);
                    fillState.put('z', k);
                }
            }
        }
    }

    protected List<Object> removeLoop(Integer quantity) {

        int x = this.fillState.get('x');
        int y = this.fillState.get('y');
        int z = this.fillState.get('z');

        List<Object> output = new ArrayList<>();

        for (int i = x; i < this.store.length; i++) {
            for (int j = y; j < this.store[0].length; j++) {
                for (int k = z; k < this.store[0][0].length; k++) {
                    if (quantity-- == 0) {
                        return output;
                    }
                    output.add(this.store[i][j][k]);
                    this.store[i][j][k] = null;
                    isFull = false;
                    this.fillState.put('x', i);
                    this.fillState.put('y', j);
                    this.fillState.put('z', k);
                    if (i == this.store.length - 1 && j == this.store[0].length - 1 && k == this.store[0][0].length - 1) {
                        isEmpty = true;
                        if (quantity > 1) throw new RuntimeException("Medium already empty");
                    }
                }
            }
        }
        return output;
    }

    /**
     * @param input
     * @param quantity
     */
    public void fill(Object input, Integer quantity) {
        if (!isFull) {
            fillLoop(input, quantity);
        }
    }

    /**
     * @param quantity
     * @return
     */
    public List<Object> remove(Integer quantity) {
        if (isEmpty || quantity > (capacity * getRelativeFillState()))
            throw new RuntimeException("Not enough stored in medium - Needed: " + quantity + " stored: " + (capacity * getRelativeFillState()));
        return removeLoop(quantity);
    }

    public Double getRelativeFillState() {
        Integer xLength = store.length;
        Integer yLength = store[0].length;
        Integer zLength = store[0][0].length;
        Long count = Arrays.stream(store)
                .flatMap(Arrays::stream)
                .flatMap(Arrays::stream)
                .collect(Collectors.toList())
                .stream()
                .filter(Objects::nonNull)
                .count();


        return 1 / ((xLength * yLength * zLength) / count.doubleValue());
    }

    public Object getSubject() {
        return subject;
    }

    public Integer getCapacity() {
        return capacity;
    }
}
