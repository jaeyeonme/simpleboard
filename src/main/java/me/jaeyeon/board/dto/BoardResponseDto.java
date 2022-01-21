package me.jaeyeon.board.dto;

import lombok.Getter;
import me.jaeyeon.board.entity.Board;

import java.io.Serializable;

@Getter
public class BoardResponseDto implements Serializable {

    private Long id;
    private String title;
    private String content;
    private String author;

    public BoardResponseDto(Board entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }
}
