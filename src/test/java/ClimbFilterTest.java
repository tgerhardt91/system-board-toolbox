import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import systemboardtoolbox.models.ClimbFilter;
import systemboardtoolbox.models.HoldFilter;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class ClimbFilterTest {

    @Test
    void testGetHoldFrames_respectsRequireTypeMatchFalse() {
        var frames = List.of("p3r4", "p29r2", "p59r1", "p65r2", "p75r3", "p89r2", "p157r4", "p158r4");
        var holdFrames = ClimbFilter.builder().holdFrames(frames).requireTypeMatch(false).build()
                .getHoldFrames();
        assertEquals(8, holdFrames.size());
        assertTrue(holdFrames.contains("p3"));
        assertTrue(holdFrames.contains("p29"));
        assertTrue(holdFrames.contains("p59"));
        assertTrue(holdFrames.contains("p65"));
        assertTrue(holdFrames.contains("p75"));
        assertTrue(holdFrames.contains("p89"));
        assertTrue(holdFrames.contains("p157"));
        assertTrue(holdFrames.contains("p158"));
    }
}
