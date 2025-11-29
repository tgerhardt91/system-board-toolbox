package org.repositories;

import org.db.Database;
import org.enums.Layout;
import org.models.Climb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ClimbRepository {
    public List<Climb> getAllClimbs(Layout layout) {
        List<Climb> climbs = new ArrayList<>();

        //todo: use JDBC param
        String sql = "SELECT * FROM climbs where layout_id = %s".formatted(
                layout.getId()
        );

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                climbs.add(
                        Climb.builder()
                                .uuid(UUID.fromString(rs.getString("uuid")))
                                .layout(Layout.fromId(rs.getInt("layout_id")))
                                .name(rs.getString("name"))
                                .setter(rs.getString("setter_username"))
                                .description(rs.getString("description"))
                                .frames(rs.getString("frames"))
                                .noMatching(rs.getBoolean("is_nomatch"))
                                .build()
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return climbs;
    }
}
