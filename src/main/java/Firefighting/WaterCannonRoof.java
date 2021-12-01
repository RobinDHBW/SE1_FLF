package Firefighting;

public class WaterCannonRoof extends WaterCannon {

    private BranchSegment segment;
    private SegmentMovable segmentMovable;

    public WaterCannonRoof(Integer sprayCapacityPerlIteration) {
        super(sprayCapacityPerlIteration);
        this.segment = new BranchSegment(3, new Integer[]{6, 6, 5});
        this.segmentMovable = new SegmentMovable(90);
    }
}
