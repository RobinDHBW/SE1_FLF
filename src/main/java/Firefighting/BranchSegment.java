package Firefighting;

import java.util.ArrayList;
import java.util.List;

public class BranchSegment {
    private final List<SegmentPart> branchSegments = new ArrayList<>();

    public BranchSegment(Integer count, Integer[] length) {
        for (int i = 0; i < count; i++) {
            branchSegments.add(new SegmentPart(length[i]));
        }
    }

}
