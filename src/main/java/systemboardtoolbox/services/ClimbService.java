package systemboardtoolbox.services;

import lombok.RequiredArgsConstructor;
import systemboardtoolbox.models.*;
import systemboardtoolbox.repositories.ClimbRepository;
import org.springframework.stereotype.Service;
import systemboardtoolbox.utils.HoldUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class ClimbService {
    private final ClimbRepository climbRepository;
    private final HoldService holdService;

    public PagedResult<Climb> getClimbs(GetClimbsRequest request) throws IOException {
        var queryData = GetClimbsQueryData.of(request);
        if(request.isIncludeClimbsWithMirroredHolds()) {
            queryData.setMirroredHoldFrames(getMirroredHoldFrames(queryData.getHoldFrames(), request.isRequireTypeMatch()));
        }
        return climbRepository.getClimbs(queryData);
    }

    private List<String> getMirroredHoldFrames(List<String> holdFrames, boolean requireTypeMatch) throws IOException {
        var mirroredHoldIdsByHoldId = HoldUtils.getMirroredHoldIdsByHoldId();
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
