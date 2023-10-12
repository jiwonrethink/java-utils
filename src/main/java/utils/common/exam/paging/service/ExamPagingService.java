package utils.common.exam.paging.service;

import org.springframework.data.domain.Pageable;
import utils.common.dto.paging.PagingResponseDto;
import utils.common.dto.paging.Pagination;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExamPagingService {

    public PagingResponseDto findAllWithPagination(Pagination pagination) {
        List<Object> result = new ArrayList<>();
        int total = 0;

        return new PagingResponseDto(result, total);
    }

    public PagingResponseDto findAllWithPagination(Pageable pageable) {
        List<Object> result = new ArrayList<>();
        int total = 0;

        return new PagingResponseDto(result, total);
    }
}
