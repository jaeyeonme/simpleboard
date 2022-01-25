package me.jaeyeon.board.modules.Posts.form;

import lombok.*;
import me.jaeyeon.board.modules.Posts.Posts;

import java.io.Serializable;
import java.time.LocalDateTime;

public class PostsDto {

    @Builder
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Request {

        private Long id;
        private String title;
        private String content;

        // DTO -> Entity
        public Posts toEntity() {
            final Posts posts = Posts.builder()
                    .id(id)
                    .title(title)
                    .content(content)
                    .build();

            return posts;
        }
    }

    @Getter
    public static class Response implements Serializable {

        private Long id;
        private String title;
        private String content;
        private String author;
        private LocalDateTime modifiedDate;

        // Entity -> DTO
        public Response(Posts posts) {
            this.id = posts.getId();
            this.title = posts.getTitle();
            this.content = posts.getContent();
            this.author = posts.getAuthor();
            this.modifiedDate = posts.getModifiedDate();
        }
    }
}
