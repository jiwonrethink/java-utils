package jiwon.java.utils.common.exam.paging.service;

import jiwon.java.utils.common.dto.paging.PagingResponseDto;
import jiwon.java.utils.common.dto.paging.Pagination;
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
