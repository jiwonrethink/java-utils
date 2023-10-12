package utils.common.exam.paging.controller;

import org.springframework.data.domain.Pageable;
import utils.common.dto.paging.PagingResponseDto;
import utils.common.util.PagingUtil;
import utils.common.util.ResponseUtil;
import utils.common.dto.paging.Pagination;
import utils.common.exam.paging.service.ExamPagingService;
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

    // 기본 페이징 (JPA)
    @GetMapping("/list/non-scroll/jpa")
    public ResponseEntity findAllByJpa(@RequestParam("page") int page,
                                  @RequestParam("limit") int limit) {
        try {
            Pagination pagination = PagingUtil.toPagination(page, limit);
            PagingResponseDto responseData = examService.findAllWithPagination(pagination);

            return ResponseUtil.SUCCESS(responseData);
        } catch (RuntimeException e) {
            return ResponseUtil.ERROR(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    // 기본 페이징 (QueryDsl)
    @GetMapping("/list/non-scroll/querydsl")
    public ResponseEntity findAllByQueryDsl(@RequestParam("page") int page,
                                  @RequestParam("limit") int limit) {
        try {
            Pagination pagination = PagingUtil.toPagination(page, limit);
            PagingResponseDto responseData = examService.findAllWithPagination(pagination);

            return ResponseUtil.SUCCESS(responseData);
        } catch (RuntimeException e) {
            return ResponseUtil.ERROR(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
    // 스크롤 페이징 (JPA)
    @GetMapping("/list/scroll/jpa")
    public ResponseEntity findAll(@RequestParam("page") int page,
                                  @RequestParam("limit") int limit,
                                  @RequestParam("add") int add) {
        try {
            Pageable pageable = PagingUtil.toPageable(page, limit, add);
            PagingResponseDto responseData = examService.findAllWithPagination(pageable);

            return ResponseUtil.SUCCESS(responseData);
        } catch (RuntimeException e) {
            return ResponseUtil.ERROR(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
    // 스크롤 페이징 (QueryDsl)
    @GetMapping("/list/scroll/querydsl")
    public ResponseEntity findAllQueryDsl(@RequestParam("page") int page,
                                  @RequestParam("limit") int limit,
                                  @RequestParam("add") int add) {
        try {
            Pagination pagination = PagingUtil.toPagination(page, limit, add);
            PagingResponseDto responseData = examService.findAllWithPagination(pagination);

            return ResponseUtil.SUCCESS(responseData);
        } catch (RuntimeException e) {
            return ResponseUtil.ERROR(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
