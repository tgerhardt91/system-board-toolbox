package systemboardtoolbox.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import systemboardtoolbox.models.Hold;
import systemboardtoolbox.models.HoldFilter;
import systemboardtoolbox.repositories.HoldRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class HoldService {

    private final HoldRepository holdRepository;

    public List<Hold> getHolds(HoldFilter holdFilter) {
        var holds = holdRepository.getHolds(holdFilter);
        var mirroredHoldIds = holds.stream().map(Hold::getMirroredHoleId)
                .toList();
        var mirroredHolds = holdRepository.getHoldsByHoleIds(mirroredHoldIds);
        for(var hold : holds) {
            var mirroredHold = mirroredHolds.stream().filter(x -> x.getHoleId().equals(hold.getMirroredHoleId()))
                    .findFirst().orElse(null);
            hold.setMirroredHold(mirroredHold);
        }

        return holds;
    };
}
