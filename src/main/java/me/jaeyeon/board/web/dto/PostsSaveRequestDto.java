package me.jaeyeon.board.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jaeyeon.board.domain.posts.Posts;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {

    private String title;
    private String content;
    private String author;

    public PostsSaveRequestDto (String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity() {
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
