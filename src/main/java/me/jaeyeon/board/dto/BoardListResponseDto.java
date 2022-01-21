package me.jaeyeon.board.dto;

import lombok.Getter;
import me.jaeyeon.board.entity.Board;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
public class BoardListResponseDto implements Serializable {

    private Long id;
    private String title;
    private String author;
    private LocalDateTime modifiedDate;

    public String getLink() {
        return "/board/update/" + id;
    }

    public BoardListResponseDto(Board entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.author = entity.getAuthor();
        this.modifiedDate = entity.getModifiedDate();
    }
}
