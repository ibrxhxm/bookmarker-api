package com.example.bookmarkerapi.service.impl;

import com.example.bookmarkerapi.domain.dto.BookmarkDTO;
import com.example.bookmarkerapi.domain.dto.response.PaginationResponse;
import com.example.bookmarkerapi.domain.model.Bookmark;
import com.example.bookmarkerapi.repository.BookmarkRepository;
import com.example.bookmarkerapi.service.BookmarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookmarkServiceImpl implements BookmarkService {
    private final BookmarkRepository bookmarkRepository;

    @Override
    @Transactional(readOnly = true)
    public PaginationResponse<BookmarkDTO> getBookmarks(int page, int size) {
        int pageNo = page < 1 ? 0 : page - 1;
        PageRequest pageRequest = PageRequest.of(pageNo, size, Sort.Direction.DESC, "createdAt");
        return new PaginationResponse<>(bookmarkRepository.findBookmarks(pageRequest));
    }
}
