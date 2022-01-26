package me.jaeyeon.board.modules.Posts;

import me.jaeyeon.board.modules.Posts.form.PostsDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.convert.DataSizeUnit;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PostsApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private PostsRepository postsRepository;

    @Autowired
    private TestRestTemplate restTemplate;

    @AfterEach
    void after() {
        postsRepository.deleteAll();
    }

    @Test
    @DisplayName("게시글 작성 Test")
    void posts_save() throws Exception {
        // given
        String title = "title";
        String content = "content";

        final PostsDto.Request requestDto = PostsDto.Request.builder()
                                                .title("title")
                                                .content("content")
                                                .author("jaeyeon")
                                                .build();


        String url = "http://localhost:" + port + "/api/v1/posts";

        // when
        final ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        final List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getContent()).isEqualTo(content);
    }

    @Test
    @DisplayName("게시글 수정 Test")
    void posts_update() throws Exception {
        // given
        String title = "title";
        String content = "content";
        final Posts savedPosts = postsRepository.save(Posts.builder()
                                            .title(title)
                                            .content(content)
                                            .author("jaeyeon")
                                            .build());

        final Long updateId = savedPosts.getId();
        String expectedTitle = "title2";
        String expectedContent = "content2";

        final PostsDto.Request requestDto = PostsDto.Request.builder().title(expectedTitle).content(expectedContent).build();

        String url = "http://localhost:" + port + "/api/v1/posts/" + updateId;

        HttpEntity<PostsDto.Request> requestEntity = new HttpEntity<>(requestDto);

        // when
        final ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        final List<Posts> all = postsRepository.findAll();

        assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle);
        assertThat(all.get(0).getContent()).isEqualTo(expectedContent);

    }
}