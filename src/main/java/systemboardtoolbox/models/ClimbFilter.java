package systemboardtoolbox.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import systemboardtoolbox.enums.Layout;
import systemboardtoolbox.utils.HoldUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClimbFilter {
    private Layout layout;
    private String setter;
    private List<String> holdFrames;
    private List<String> mirroredHoldFrames;
    private boolean requireTypeMatch;
    private boolean includeClimbsWithMirroredHolds;

    public List<String> getHoldFrames() {
        if(!requireTypeMatch) {
            return holdFrames.stream().map(HoldUtils::removeRIdFromFrame)
                    .collect(Collectors.toList());
        }

        return holdFrames;
    }
}
