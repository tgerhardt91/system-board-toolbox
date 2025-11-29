package systemboardtoolbox.repositories;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import systemboardtoolbox.enums.Layout;
import systemboardtoolbox.models.Climb;
import systemboardtoolbox.models.GetClimbsQueryData;
import systemboardtoolbox.models.PagedResult;

import java.util.*;

@Repository
@RequiredArgsConstructor
public class ClimbRepository {
    private final NamedParameterJdbcTemplate jdbc;

    public PagedResult<Climb> getClimbs(GetClimbsQueryData queryData) {
        var pagingOffset = queryData.getPage() * queryData.getPageSize();

        var sqlStringBuilder = new StringBuilder();

        var params = new HashMap<String, Object>();
        var whereClause = getWhereClause(queryData, params);

        //count query
        String countSql = "SELECT COUNT(*) FROM climbs c " + whereClause;
        int count = jdbc.queryForObject(countSql, params, Integer.class);

        //main query
        String baseSql = "SELECT * FROM climbs c";
        sqlStringBuilder.append(baseSql);
        sqlStringBuilder.append(whereClause);

        sqlStringBuilder.append("LIMIT :limit OFFSET :offset");
        params.put("limit", queryData.getPageSize());
        params.put("offset", pagingOffset);

        var mapper = new Climb.ClimbRowMapper();
        var climbs = jdbc.query(sqlStringBuilder.toString(), params, mapper);

        return new PagedResult<>(
                climbs,
                count,
                queryData.getPage(),
                queryData.getPageSize()
        );
    }

    private String getWhereClause(GetClimbsQueryData queryData, Map<String, Object> params) {
        var hasSetterFilter = !Strings.isBlank(queryData.getSetter());
        var hasLayoutFilter = queryData.getLayout() != null && !queryData.getLayout().equals(Layout.UNKNOWN);
        var hasHoldFilter = queryData.getHoldFrames() != null
                && !queryData.getHoldFrames().isEmpty();
        var hasMirroredHoldFilter = queryData.getMirroredHoldFrames() != null
                && !queryData.getMirroredHoldFrames().isEmpty();

        var sb = new StringBuilder();

        sb.append(" WHERE 1 = 1");
        if (hasSetterFilter) {
            sb.append(" AND c.setter_username = :setterUsername");
            params.put("setterUsername", queryData.getSetter());
        }
        if (hasLayoutFilter) {
            sb.append(" AND c.layout_id = :layoutId");
            params.put("layoutId", queryData.getLayout().getId());
        }
        if (hasHoldFilter) {
            var holdFrameLists = new ArrayList<List<String>>();
            holdFrameLists.add(queryData.getHoldFrames());
            if(hasMirroredHoldFilter) {
                holdFrameLists.add(queryData.getMirroredHoldFrames());
            }
            var frameFilter = buildFrameFilterGroups(holdFrameLists, params);
            sb.append(frameFilter);
        }

        return sb.toString();
    }

    private String buildFrameFilterGroups(
            List<List<String>> frameGroups,
            Map<String, Object> params
    ) {
        StringBuilder sb = new StringBuilder(" AND (");
        int groupIndex = 0;
        for (List<String> group : frameGroups) {
            sb.append("(");
            int i = 0;
            for (String frame : group) {
                String paramName = "filterFrame_" + groupIndex + "_" + i++;
                sb.append("c.frames LIKE '%' || :").append(paramName).append(" || '%' AND ");
                params.put(paramName, frame);
            }
            // Remove trailing AND
            sb.setLength(sb.length() - 5);
            sb.append(") OR ");
            groupIndex++;
        }

        // Remove trailing OR
        sb.setLength(sb.length() - 4);
        sb.append(")");
        return sb.toString();
    }
}
