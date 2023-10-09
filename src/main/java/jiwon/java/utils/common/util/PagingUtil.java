package jiwon.java.utils.common.util;

import jiwon.java.utils.common.dto.paging.Pagination;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class PagingUtil {

    public static Pageable toPageable(int page, int limit) {

        return PageRequest.of(page, limit);
    }

    public static Pageable toPageable(int page, int limit, int add) {

        return PageRequest.of(page, limit+add);
    }

    public static Pagination toPagination(int page, int limit) {
        return new Pagination(page * limit, limit);
    }

    public static Pagination toPagination(int page, int limit, int add) {
        return new Pagination(page * limit, limit+add);
    }
}