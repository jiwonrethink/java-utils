package common.jiwon.java.common.utils.exam.paging.controller;

import common.jiwon.java.common.utils.dto.paging.Pagination;
import common.jiwon.java.common.utils.dto.paging.PagingResponseDto;
import common.jiwon.java.common.utils.exam.paging.service.ExamPagingService;
import common.jiwon.java.common.utils.util.PagingUtil;
import common.jiwon.java.common.utils.util.ResponseUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ExamPagingController {

    private final ExamPagingService examService;

    // 기본 페이징
    @GetMapping("/list/non-scroll")
    public ResponseEntity findAll(@RequestParam("page") int page,
                                  @RequestParam("limit") int limit) {
        try {
            Pagination pagination =
                    PagingUtil.toPagination(page, limit);
            PagingResponseDto responseData = examService.findAllWithPagination(pagination);

            return ResponseUtil.SUCCESS(responseData);
        } catch (RuntimeException e) {
            return ResponseUtil.ERROR(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
    // 스크롤 페이징
    @GetMapping("/list/scroll")
    public ResponseEntity findAll(@RequestParam("page") int page,
                                  @RequestParam("limit") int limit,
                                  @RequestParam("add") int add) {
        try {
            Pagination pagination =
                    PagingUtil.toPagination(page, limit, add);
            PagingResponseDto responseData = examService.findAllWithPagination(pagination);

            return ResponseUtil.SUCCESS(responseData);
        } catch (RuntimeException e) {
            return ResponseUtil.ERROR(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

}
