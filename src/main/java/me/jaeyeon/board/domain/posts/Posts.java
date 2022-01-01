package me.jaeyeon.board.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jaeyeon.board.domain.BaseEntity;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Posts extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 450, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;
    private String author;

    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}