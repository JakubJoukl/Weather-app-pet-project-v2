package com.example.weatherapppetprojectv2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PagingDetailDto {
    private int pageNumber;
    private int pageSize;
    private int totalPages;
}
