package me.jaeyeon.board.repository;

import me.jaeyeon.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    @Query(value = "SELECT b FROM Board b ORDER BY b.id DESC")
    List<Board> findAllDesc();
}
