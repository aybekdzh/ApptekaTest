package com.example.apptekatest;

import org.junit.jupiter.api.Test;
import io.mockk.every
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import com.ninjasquad.springmockk.MockkBean

@WebMvcTest
class ApptekaTests(@Autowired val mockMvc: MockMvc) {
    @MockkBean
    lateinit var itemRepository: ItemRepository

    @MockkBean
    lateinit var articleRepository: ArticleRepository

    @Test
    fun `List articles`() {
        val johnDoe = User("johnDoe", "John", "Doe")
        val lorem5Article = Article("Lorem", "Lorem", "dolor sit amet", johnDoe)
        val ipsumArticle = Article("Ipsum", "Ipsum", "dolor sit amet", johnDoe)
        every { articleRepository.findAllByOrderByAddedAtDesc() } returns listOf(lorem5Article, ipsumArticle)
        mockMvc.perform(get("/api/article/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("\$.[0].author.login").value(johnDoe.login))
                .andExpect(jsonPath("\$.[0].slug").value(lorem5Article.slug))
                .andExpect(jsonPath("\$.[1].author.login").value(johnDoe.login))
                .andExpect(jsonPath("\$.[1].slug").value(ipsumArticle.slug))
    }
}
