package com.example.bookmarkerapi.util;

import com.example.bookmarkerapi.domain.model.Bookmark;
import com.example.bookmarkerapi.repository.BookmarkRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@AllArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final BookmarkRepository bookmarkRepository;
    @Override
    public void run(String... args) throws Exception {
        bookmarkRepository.save(new Bookmark(null, "Google", "https://www.google.com", LocalDateTime.now()));
        bookmarkRepository.save(new Bookmark(null, "Amazon", "https://www.amazon.com", LocalDateTime.now()));
        bookmarkRepository.save(new Bookmark(null, "Facebook", "https://www.facebook.com", LocalDateTime.now()));
        bookmarkRepository.save(new Bookmark(null, "Twitter", "https://www.twitter.com", LocalDateTime.now()));
    }
}
