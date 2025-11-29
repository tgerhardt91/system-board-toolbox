package systemboardtoolbox.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import systemboardtoolbox.models.Hold;
import systemboardtoolbox.models.HoldFilter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class HoldRepository {

    private final NamedParameterJdbcTemplate jdbc;

    public List<Hold> getHolds(HoldFilter holdFilter) {
        var hasHoldIdsFilter = holdFilter != null
                && holdFilter.getHoldFrames() != null
                && !holdFilter.getHoldFrames().isEmpty();

        var sqlStringBuilder = new StringBuilder();
        String baseSql = """
                    SELECT p.id, p.default_placement_role_id, h.id as hole_id, h.name, h.x, h.y, h.mirrored_hole_id FROM placements p
                    INNER JOIN holes h ON h.id = p.hole_id
                """;
        sqlStringBuilder.append(baseSql);

        sqlStringBuilder.append(" WHERE 1 = 1");
        Map<String, Object> params = new HashMap<>();
        if (hasHoldIdsFilter) {
            sqlStringBuilder.append(" AND p.id IN (:ids)");
            params.put("ids", holdFilter.getHoldIdsFromFrames());
        }

        var mapper = new Hold.HoldRowMapper();
        return jdbc.query(sqlStringBuilder.toString(), params, mapper);
    }

    public List<Hold> getHoldsByHoleIds(List<Integer> holeIds) {
        var sqlStringBuilder = new StringBuilder();
        String baseSql = """
                    SELECT p.id, p.default_placement_role_id, h.id as hole_id, h.name, h.x, h.y, h.mirrored_hole_id FROM placements p
                    INNER JOIN holes h ON h.id = p.hole_id
                """;
        sqlStringBuilder.append(baseSql);

        sqlStringBuilder.append(" WHERE 1 = 1");
        Map<String, Object> params = new HashMap<>();
        sqlStringBuilder.append(" AND h.id IN (:holeIds)");
        params.put("holeIds", holeIds);

        var mapper = new Hold.HoldRowMapper();
        return jdbc.query(sqlStringBuilder.toString(), params, mapper);
    }
}
