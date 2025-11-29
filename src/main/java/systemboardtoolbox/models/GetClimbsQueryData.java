package systemboardtoolbox.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import systemboardtoolbox.enums.Layout;
import systemboardtoolbox.utils.HoldUtils;

import java.util.List;

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

    public static GetClimbsQueryData of(GetClimbsRequest request) {
        return GetClimbsQueryData.builder()
                .page(request.getPage())
                .pageSize(request.getPageSize())
                .layout(request.getLayout())
                .setter(request.getSetter())
                .holdFrames(request.isRequireTypeMatch() ? request.getHoldFrames() :
                        HoldUtils.getHoldFramesWithoutRId(request.getHoldFrames()))
                .build();
    }
}
