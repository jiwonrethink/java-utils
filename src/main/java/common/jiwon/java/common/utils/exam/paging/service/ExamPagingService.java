package common.jiwon.java.common.utils.exam.paging.service;

import common.jiwon.java.common.utils.dto.paging.Pagination;
import common.jiwon.java.common.utils.dto.paging.PagingResponseDto;
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
}
