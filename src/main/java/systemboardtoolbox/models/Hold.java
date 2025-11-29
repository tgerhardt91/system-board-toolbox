package systemboardtoolbox.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import systemboardtoolbox.enums.HoldRole;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Hold {
    private Integer id;
    private Integer holeId;
    private HoldRole defaultRole;
    private String readableLocation;
    private Integer x;
    private Integer y;
    private Integer mirroredHoleId;
    private Hold MirroredHold;

    public static class HoldRowMapper implements RowMapper<Hold> {
        @Override
        public Hold mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Hold.builder()
                    .id(rs.getInt("id"))
                    .holeId(rs.getInt("hole_id"))
                    .defaultRole(HoldRole.fromId(rs.getInt("default_placement_role_id")))
                    .readableLocation(rs.getString("name"))
                    .x(rs.getInt("x"))
                    .y(rs.getInt("y"))
                    .mirroredHoleId(rs.getInt("mirrored_hole_id"))
                    .build();
        }
    }
}