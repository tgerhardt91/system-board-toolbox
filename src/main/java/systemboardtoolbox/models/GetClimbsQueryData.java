package systemboardtoolbox.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import systemboardtoolbox.enums.Layout;
import systemboardtoolbox.utils.HoldUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetClimbsQueryData {
    @Builder.Default
    private Integer page = 0;
    @Builder.Default
    private Integer pageSize = 10;
    private Layout layout;
    private String setter;
    private List<String> holdFrames;
    private List<String> mirroredHoldFrames;
    private Integer minGrade;
    private Integer maxGrade;
    @Builder.Default
    private Integer angle = 40;

    public static GetClimbsQueryData of(GetClimbsRequest request) {
        var queryData = GetClimbsQueryData.builder()
                .page(request.getPage())
                .pageSize(request.getPageSize())
                .layout(request.getLayout())
                .setter(request.getSetter())
                .holdFrames(request.isRequireTypeMatch() ? request.getHoldFrames() : HoldUtils.getHoldFramesWithoutRId(request.getHoldFrames()))
                .minGrade(request.getMinGrade())
                .maxGrade(request.getMaxGrade())
                .angle(request.getAngle())
                .build();

       if(request.isIncludeClimbsWithMirroredHolds()) {
           queryData.setMirroredHoldFrames(getMirroredHoldFramesFromHoldFrames(request.getHoldFrames(), request.isRequireTypeMatch()));
       }

       return queryData;
    }

    public boolean hasSetter() {
        return !Strings.isBlank(getSetter());
    }

    public boolean hasLayout() {
        return getLayout() != null && !getLayout().equals(Layout.UNKNOWN);
    }

    public boolean hasHoldFrames() {
        return getHoldFrames() != null && !getHoldFrames().isEmpty();
    }

    public boolean hasMirroredHoldFrames() {
        return getMirroredHoldFrames() != null && !getMirroredHoldFrames().isEmpty();
    }

    public boolean hasMinGrade() {
        return getMinGrade() != null;
    }

    public boolean hasMaxGrade() {
        return getMaxGrade() != null;
    }

    public boolean hasAngle() {
        return getAngle() != null;
    }

    private static List<String> getMirroredHoldFramesFromHoldFrames(List<String> holdFrames, boolean requireTypeMatch) {
        Map<Integer, Integer> mirroredHoldIdsByHoldId = null;
        try {
            mirroredHoldIdsByHoldId = HoldUtils.getMirroredHoldIdsByHoldId();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        var mirroredHoldFrames = new ArrayList<String>();

        for(var holdFrame : holdFrames) {
            var rId = HoldUtils.extractRIdFromFrame(holdFrame);
            var pId = HoldUtils.extractPIdFromFrame(holdFrame);
            var mirroredHoldId = mirroredHoldIdsByHoldId.get(pId);
            mirroredHoldFrames.add("p%sr%s".formatted(mirroredHoldId, rId));
        }

        return requireTypeMatch ? mirroredHoldFrames : HoldUtils.getHoldFramesWithoutRId(mirroredHoldFrames);
    }
}
