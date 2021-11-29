public class Seat {
    protected int seatRow = 0;
    protected boolean leftSide = false;
    protected boolean occupied = false;
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

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }
}