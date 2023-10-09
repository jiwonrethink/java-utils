package common.jiwon.java.common.utils.dto.paging;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PagingResponseDto<T> {

    private T result;
    private int total;
}
