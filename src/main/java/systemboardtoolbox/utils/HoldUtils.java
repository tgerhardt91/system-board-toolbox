package systemboardtoolbox.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;
import systemboardtoolbox.enums.GradeType;
import systemboardtoolbox.models.MirroredHold;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@UtilityClass
public class HoldUtils {

    public static List<String> getHoldFramesWithoutRId(List<String> holdFrames) {
        return holdFrames.stream().map(HoldUtils::removeRIdFromFrame)
                .toList();
    }

    public static String removeRIdFromFrame(String frame) {
        return frame.replaceAll("r\\d+", "");
    }

    public static Integer extractRIdFromFrame(String frame) {
        return Integer.valueOf(frame.replaceAll("p\\d+r(\\d+)", "$1"));
    }

    public static Integer extractPIdFromFrame(String frame) {
        return Integer.valueOf(frame.replaceAll("p(\\d+)(?:r\\d+)?", "$1"));
    }

    public static Map<Integer, Integer> getMirroredHoldIdsByHoldId() throws IOException {
        var mapper = new ObjectMapper();

        try(InputStream inputStream =
                    HoldUtils.class.getResourceAsStream("/data/mirrored_holds_by_pid.json")) {
            var mirroredHoldPojos = mapper.readValue(inputStream, new TypeReference<List<MirroredHold>>() {
            });
            return mirroredHoldPojos.stream().collect(Collectors.toMap(MirroredHold::getId, MirroredHold::getMirroredId));
        }
    }
}
