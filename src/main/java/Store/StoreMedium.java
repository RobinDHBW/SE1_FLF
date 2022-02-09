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

    protected void fillLoop(Object input, Double quantity) {
        int x = fillState.get('x');
        int y = fillState.get('y');
        int z = fillState.get('z');

        /**
         * Count backwards from full to 0
         * x == max length - to count to 0, we need to start with x-1
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

    protected List<Object> removeLoop(Double quantity) {
        try {
        int x = fillState.get('x');
        int y = fillState.get('y');
        int z = fillState.get('z');

        List<Object> output = new ArrayList<>();

        for (int i = x; i < store.length; i++) {
            for (int j = y; j < store[0].length; j++) {
                for (int k = z; k < store[0][0].length; k++) {
                    if (j == store[0].length && i == store.length && k == store[0][0].length) {
                        isEmpty = true;
                        if(quantity > 0) throw new Exception("Medium already empty");
                    }
                    //if(Objects.isNull(store[i][j][k])) continue;
                    if (quantity-- == 0) return output;
                    output.add(store[i][j][k]);
                    store[i][j][k] = null;
                    isFull = false;
                    fillState.put('x', i);
                    fillState.put('y', j);
                    fillState.put('z', k);
                }
            }
        }
        return output;
        }catch (Exception ex){
            System.err.println(ex.getMessage());
            System.err.println(Arrays.toString(ex.getStackTrace()));
            return null;
        }
    }

    /**
     * @param input
     * @param quantity
     */
    public void fill(Object input, Double quantity) {
        if (!isFull) {
            fillLoop(input, quantity);
        }
    }

    /**
     * @param quantity
     * @return
     */
    public List<Object> remove(Double quantity) {
        if (!isEmpty) {
            return removeLoop(quantity);
        }
        return null;
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
