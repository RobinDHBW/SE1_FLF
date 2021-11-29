public class Seat {
    protected Integer seatRow = 0;
    protected Boolean leftSide = false;
    protected Boolean occupied = false;
    public Respirator respirator = new Respirator();

    public Seat(Integer seatRow, Boolean leftSide, Boolean occupied) {
        this.seatRow = seatRow;
        this.leftSide = leftSide;
        this.occupied = occupied;

    }

    public Integer getSeatRow() {
        return seatRow;
    }

    public Boolean getLeftSide() {
        return leftSide;
    }

    public Boolean getOccupied() {
        return occupied;
    }
}