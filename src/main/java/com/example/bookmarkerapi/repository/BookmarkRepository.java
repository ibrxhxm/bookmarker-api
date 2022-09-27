package com.example.bookmarkerapi.repository;

import com.example.bookmarkerapi.domain.dto.BookmarkDTO;
import com.example.bookmarkerapi.domain.model.Bookmark;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

    @Query("select new com.example.bookmarkerapi.domain.dto.BookmarkDTO(b.id, b.title, b.url) from Bookmark b")
    Page<BookmarkDTO> findBookmarks(Pageable pageable);
}
