package systemboardtoolbox.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import systemboardtoolbox.enums.Layout;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Climb {
    private String uuid;
    private String name;
    private Layout layout;
    private String setter;
    private String description;
    private String frames;
    private boolean noMatching;

    public static class ClimbRowMapper implements RowMapper<Climb> {
        @Override
        public Climb mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Climb.builder()
                    .uuid(rs.getString("uuid"))
                    .layout(Layout.fromId(rs.getInt("layout_id")))
                    .name(rs.getString("name"))
                    .setter(rs.getString("setter_username"))
                    .description(rs.getString("description"))
                    .frames(rs.getString("frames"))
                    .noMatching(rs.getBoolean("is_nomatch"))
                    .build();
        }
    }
}