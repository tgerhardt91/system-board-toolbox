package systemboardtoolbox.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PagedResult<T> {
    private List<T> items;
    private int total;
    private int page;
    private int pageSize;
}
