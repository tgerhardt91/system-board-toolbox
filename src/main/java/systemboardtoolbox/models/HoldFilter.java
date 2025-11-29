package systemboardtoolbox.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import systemboardtoolbox.utils.HoldUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HoldFilter {
    private List<String> holdFrames;

    public List<Integer> getHoldIdsFromFrames() {
        var holdIds = new ArrayList<Integer>();
        for(var frame : holdFrames) {
            holdIds.add(HoldUtils.extractPIdFromFrame(frame));
        }

        return holdIds;
    }
}
