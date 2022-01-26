package me.jaeyeon.board.modules.Posts;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @AfterEach
    void afeter() {
        postsRepository.deleteAll();
    }

    @Test
    @DisplayName("게시글 저장")
    void posts_save() throws Exception {
        // given
        String title = "title";
        String content = "content";

        postsRepository.save(Posts.builder()
                                .title(title)
                                .content(content)
                                .author("Jaeyeon")
                                .build());

        // when
        final List<Posts> postsList = postsRepository.findAll();

        // then
        final Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    @DisplayName("JPA Auditing Test")
    void BaseTimeEntity_Test() throws Exception {
        // given
        final LocalDateTime now = LocalDateTime.of(2022, 1, 26, 0, 0, 0);
        postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("jaeyeon")
                .build());

        // when
        final List<Posts> postsList = postsRepository.findAll();

        // then
        final Posts posts = postsList.get(0);

        System.out.println(">>>>>>>>>> createDate = " + posts.getCreatedDate() + ", modifiedDate = " + posts.getModifiedDate());
        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);
    }

}