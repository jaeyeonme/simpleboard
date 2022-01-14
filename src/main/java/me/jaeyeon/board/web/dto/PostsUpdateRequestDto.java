package me.jaeyeon.board.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor
public class PostsUpdateRequestDto {

    @NotEmpty(message = "글 제목을 입력해주시기 바랍니다.")
    private String title;

    @NotEmpty(message = "글 내용을 입력해주시기 바랍니다.")
    private String content;

    @Builder
    public PostsUpdateRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
