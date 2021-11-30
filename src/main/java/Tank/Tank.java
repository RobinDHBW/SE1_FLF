package Tank;

import java.util.HashMap;

public class Tank implements ITank {
    TankSubject[][][] tank;
    TankSubject subject;
    HashMap<Character, Integer> fillState = new HashMap<>();
    Boolean isFull = false;

    public Tank(TankSubject subject, Integer length, Integer height, Integer width) {
        tank = new TankSubject[length][height][width];
        this.subject = subject;

        fillState.put('x', length);
        fillState.put('y', height);
        fillState.put('z', width);
    }

    @Override
    public void fill(TankSubject input, Integer quantity) {
        int x = fillState.get('x');
        int y = fillState.get('y');
        int z = fillState.get('z');

        if (!isFull) {
            for (int j = y; j > 0; j--) {
                for (int i = x; i > 0; i--) {
                    for (int k = z; k > 0; k--) {
                        if (j == 1 && i == 1 && k == 1) isFull = true;
                        if (quantity-- == 0) break;
                        tank[i][j][k] = input;
                        fillState.put('x', i);
                        fillState.put('y', j);
                        fillState.put('z', k);
                        fillState.put('z', k);
                    }
                }
            }
        }
    }

    @Override
    public TankSubject remove(Integer quantity) {
        return null;
    }

    public Boolean getFull() {
        return isFull;
    }

    public HashMap<Character, Integer> getFillState() {
        return fillState;
    }

    public TankSubject getSubject() {
        return subject;
    }
}
