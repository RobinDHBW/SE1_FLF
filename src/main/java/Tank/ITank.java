package Tank;

public interface ITank {
    void fill(TankSubject input, Integer quantity);
    TankSubject remove(Integer quantity);
}
