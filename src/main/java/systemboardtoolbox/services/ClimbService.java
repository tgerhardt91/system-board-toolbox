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

    public PagedResult<Climb> getClimbs(GetClimbsRequest request) {
        var queryData = GetClimbsQueryData.of(request);
        return climbRepository.getClimbs(queryData);
    }
}
