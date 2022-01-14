package me.jaeyeon.board.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jaeyeon.board.domain.posts.Posts;

import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {

    @NotEmpty(message = "글 제목을 입력해주시기 바랍니다.")
    private String title;

    @NotEmpty(message = "글 내용을 입력해주시기 바랍니다.")
    private String content;

    @NotEmpty(message = "글 작성자을 입력해주시기 바랍니다.")
    private String author;

    @Builder // builder 클래스 자동 생성 -> 생성자 대신 사용
    public PostsSaveRequestDto (String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    // Dto에서 필요한 부분을 entity화 시킴
    public Posts toEntity() {
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
