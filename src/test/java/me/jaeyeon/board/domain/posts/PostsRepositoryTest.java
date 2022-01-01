package me.jaeyeon.board.domain.posts;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @AfterEach
    void after() {
        postsRepository.deleteAll();
    }

    @Test
    @DisplayName("게시글 저장")
    void Posts_save() throws Exception {
        // given
        String title = "Title";
        String content = "Content";

        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("author")
                .build());

        // when
        List<Posts> postsList = postsRepository.findAll();

        // then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    @DisplayName("BaseTimeEntity Test")
    void baseTimeEntity_test() throws Exception {
        // given
        LocalDateTime now = LocalDateTime.of(2021, 12, 30, 0, 0, 0);
        postsRepository.save(Posts.builder()
                .title("title")
                .content("contents")
                .author("author")
                .build());

        // when
        List<Posts> postsList = postsRepository.findAll();

        // then
        Posts posts = (Posts) postsList.get(0);

        System.out.println(">>>>>> createDate=" + posts.getCreatedDate() + ", modifiedDate" + posts.getModifiedDate());

        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);
    }

}