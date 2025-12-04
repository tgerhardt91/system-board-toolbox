package systemboardtoolbox.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import systemboardtoolbox.enums.Layout;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetClimbsRequest {
    @Builder.Default
    private Integer page = 0;
    @Builder.Default
    private Integer pageSize = 10;
    private Layout layout;
    private String setter;
    private List<String> holdFrames;
    private boolean requireTypeMatch;
    private boolean includeClimbsWithMirroredHolds;
    //todo: add min/max validation
    private Integer minGrade;
    //todo: add min/max validation
    private Integer maxGrade;
    private Integer angle;
}
