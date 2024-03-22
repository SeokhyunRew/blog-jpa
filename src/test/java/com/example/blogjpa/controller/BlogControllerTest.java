package com.example.blogjpa.controller;

import com.example.blogjpa.domain.Article;
import com.example.blogjpa.dto.AddArticleRequest;
import com.example.blogjpa.repository.BlogRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
public class BlogControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private BlogRepository blogRepository;

    @BeforeEach
    public void mockMvcSetUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        blogRepository.deleteAll();
    }

    @DisplayName("블로그 글 추가 성공")
    @Test
    public void addAriticle() throws Exception {
        // given, articles api 불러옴
        String url = "/api/articles";
        String title = "title";
        String content = "contents";
        AddArticleRequest request = new AddArticleRequest(title, content);

        // 객체를 JSON으로 직렬화
        String requestBody = objectMapper.writeValueAsString(request);

        // when : API 요청 => POST /api/articles
        ResultActions result = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                .contentType("application/json") 위에 줄이랑 같은 것, control T 누르면 볼수있음.
                .content(requestBody));

        // then httpstatuscode 201검증 > {"title" : "제목", "content" : "내용"}
        //2가지 방식 있음 1번째 는 직접 db에서 뽑아와서 검증이고 2번째는 넣는 제이슨의 값 뽑아아서 검증?

        /*result.andExpect(status().isCreated());

        List<Article> articleList = blogRepository.findAll();

        assertThat(articleList.size()).isEqualTo(1);
        assertThat(articleList.get(0).getTitle()).isEqualTo(title);
        assertThat(articleList.get(0).getContent()).isEqualTo(content);*/

        result.andExpect(status().isCreated())
                .andExpect(jsonPath("title").value(request.getTitle()))
                .andExpect(jsonPath("content").value(request.getContent()));
    }

    @DisplayName("블로그 글 전체 조회 성공")
    @Test
    public void showArticle() throws Exception {

        //---------------------블로그 단건 조회------------
        /*// given : blogRepository.save
        final String url = "/api/articles";
        final String title = "제목";
        final String content = "내용";
        Article article = blogRepository.save(new Article(title, content));

        // when : GET /api/articles
        ResultActions result = mockMvc.perform(get(url));

        // then : 호출결과(Json)와 save한 데이터의 비교
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value(article.getTitle()))
                .andExpect(jsonPath("$[0].content").value(article.getContent()));*/

        //----------------------------블로그 전체 조회-----------------
        // given : blogRepository.save
        List<Article> articleList = new ArrayList<>();
        Article article1 = new Article("title1", "content1");
        Article article2 = new Article("title2", "content2");
        articleList.add(article1);
        articleList.add(article2);
        blogRepository.saveAll(articleList);

        // when : GET /api/articles
        ResultActions resultActions = mockMvc.perform(get("/api/articles"));

        //then  : 호출결과(Json)와 save한 데이터와 비교
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value(articleList.get(0).getTitle()))
                .andExpect(jsonPath("$[0].content").value(articleList.get(0).getContent()))
                .andExpect(jsonPath("$[1].title").value(articleList.get(1).getTitle()))
                .andExpect(jsonPath("$[1].content").value(articleList.get(1).getContent()));
    }

    @DisplayName("블로그 글 삭제 성공")
    @Test
    public void testDeleteArticle() throws Exception {
        // given
        final String url = "/api/articles/{id}";
        String title = "제목1";
        String content = "내용1";

        Article article = blogRepository.save(new Article(title, content));
        Long savedId = article.getId();

        // when
        mockMvc.perform(delete(url, savedId)).andExpect(status().isOk());

        // then
        List<Article> afterDeleteList = blogRepository.findAll();
        assertTrue(afterDeleteList.isEmpty());
    }

    @DisplayName("블로그 수정 성공")
    @Test
    public void updateArticle() throws Exception {
        // given
        String url = "/api/articles/{id}";
        String title = "제목";
        String content = "내용";
        Article article = blogRepository.save(new Article(title, content));

        String modifiedTitle = "제목수정";
        String modifiedContent = "내용수정";
        AddArticleRequest request = new AddArticleRequest(modifiedTitle, modifiedContent);

        // when
        ResultActions result = mockMvc.perform(put(url, article.getId())
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON_VALUE));

        // then
        result.andExpect(status().isOk());

        Article afterModifiedArticle = blogRepository.findById(article.getId())
                .orElseThrow(() -> new IllegalArgumentException());
        assertThat(afterModifiedArticle.getTitle()).isEqualTo(modifiedTitle);
        assertThat(afterModifiedArticle.getContent()).isEqualTo(modifiedContent);
    }
}