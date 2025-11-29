package utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import systemboardtoolbox.utils.HoldUtils;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class HoldUtilsTest {

    @Test
    void test_getMirroredHoldIdsByHoldId() throws IOException {
        var mirroredHoldMap = HoldUtils.getMirroredHoldIdsByHoldId();
        assertEquals(498, mirroredHoldMap.size());
        assertEquals(540, mirroredHoldMap.get(422));
    }
}
