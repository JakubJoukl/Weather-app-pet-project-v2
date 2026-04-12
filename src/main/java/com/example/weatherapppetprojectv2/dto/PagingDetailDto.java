package com.example.weatherapppetprojectv2.dto;

import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
public class PagingDetailDto {
    private int pageNumber;
    private int pageSize;
    private int totalPages;
    private long totalElements;
    private boolean first;
    private boolean last;

    public PagingDetailDto(int pageNumber, int pageSize, int totalPages, long totalElements, boolean first, boolean last) {
        this.pageNumber = pageNumber + 1;
        this.pageSize = pageSize;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.first = first;
        this.last = last;
    }

    public PagingDetailDto(Page<?> page) {
        this(
            page.getNumber(),
            page.getSize(),
            page.getTotalPages(),
            page.getTotalElements(),
            page.isFirst(),
            page.isLast()
        );
    }
}
