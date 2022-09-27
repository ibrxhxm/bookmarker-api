package com.example.bookmarkerapi.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource(properties = {
        "spring.datasource.url=jdbc:tc:postgresql:14-alpine:///demo"
})
class BookmarkControllerTest {
    @Autowired
    private MockMvc mockMvc;


    @Test
    void shouldGetBookmarks() throws Exception {
        mockMvc. perform(get("/bookmarks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.total_elements").value(16))
                .andExpect(jsonPath("$.total_pages").value(2))
                .andExpect(jsonPath("$.current_page").value(1))
                .andExpect(jsonPath("$.is_first").value(true))
                .andExpect(jsonPath("$.is_last").value(false))
                .andExpect(jsonPath("$.has_next").value(true))
                .andExpect(jsonPath("$.has_previous").value(false));
    }

    @ParameterizedTest
    @CsvSource({
            "1, 16, 2, 1, true, false, true, false",
            "2, 16, 2, 2, false, true, false, true"
    })
    void shouldGetBookmarks(int page, int totalElements, int totalPages, int currentPage,
                            boolean isFirst, boolean isLast, boolean hasNext, boolean hasPrevious) throws Exception {
        mockMvc. perform(get("/bookmarks?page=%d".formatted(page)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.total_elements").value(totalElements))
                .andExpect(jsonPath("$.total_pages").value(totalPages))
                .andExpect(jsonPath("$.current_page").value(currentPage))
                .andExpect(jsonPath("$.is_first").value(isFirst))
                .andExpect(jsonPath("$.is_last").value(isLast))
                .andExpect(jsonPath("$.has_next").value(hasNext))
                .andExpect(jsonPath("$.has_previous").value(hasPrevious));
    }
}