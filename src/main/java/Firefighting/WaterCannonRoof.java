package Firefighting;

public class WaterCannonRoof extends WaterCannon {

    private BranchSegment segment;
    private SegmentMovable segmentMovable;

    public WaterCannonRoof() {
        this.segment = new BranchSegment(3, new Integer[]{6, 6, 5});
        this.segmentMovable = new SegmentMovable(90);
    }

    @Override
    public void toggle() {
        super.toggle();
        this.segmentMovable.move(this.state);
    }

    public void move(Boolean moveUp) {
        this.segmentMovable.move(moveUp);
    }
}
