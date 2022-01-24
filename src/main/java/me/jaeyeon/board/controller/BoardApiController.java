package me.jaeyeon.board.controller;

import lombok.RequiredArgsConstructor;
import me.jaeyeon.board.dto.BoardResponseDto;
import me.jaeyeon.board.dto.BoardSaveRequestDto;
import me.jaeyeon.board.dto.BoardUpdateRequestDto;
import me.jaeyeon.board.service.BoardService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class BoardApiController {

    private final BoardService boardService;

    @PostMapping("/api/v1/board")
    public Long save(@RequestBody BoardSaveRequestDto boardSaveRequestDto) {
        return boardService.save(boardSaveRequestDto);
    }

    @PutMapping("/api/v1/board/{id}")
    public Long update(@PathVariable Long id, @RequestBody BoardUpdateRequestDto boardUpdateRequestDto) {
        return boardService.update(id, boardUpdateRequestDto);
    }

    @GetMapping("/api/v1/board/{id}")
    public BoardResponseDto findById(@PathVariable Long id) {
        return boardService.findById(id);
    }

    @DeleteMapping("/api/v1/board/{id}")
    public Long delete(@PathVariable Long id) {
        boardService.delete(id);
        return id;
    }
}
