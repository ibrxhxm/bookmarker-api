package com.example.bookmarkerapi.service;

import com.example.bookmarkerapi.domain.dto.BookmarkDTO;
import com.example.bookmarkerapi.domain.dto.response.PaginationResponse;


public interface BookmarkService {
    PaginationResponse<BookmarkDTO> getBookmarks(int page, int size);
}
