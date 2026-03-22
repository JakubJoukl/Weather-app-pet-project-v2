package com.example.weatherapppetprojectv2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PagedResponseDto <T> {
    private T data;
    private PagingDetailDto pagingDetailDto;
}
