package systemboardtoolbox.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MirroredHold {
    private Integer id;
    @JsonProperty("mirrored_id")
    private Integer mirroredId;
}
