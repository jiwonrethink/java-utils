package common.jiwon.java.common.utils.dto.paging;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Pagination {
    private int offset;
    private int limit;
}