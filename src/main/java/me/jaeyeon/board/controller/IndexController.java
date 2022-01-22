package me.jaeyeon.board.controller;

import lombok.RequiredArgsConstructor;
import me.jaeyeon.board.dto.BoardResponseDto;
import me.jaeyeon.board.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final BoardService boardService;

    @GetMapping("/")
    public String main(Model model) {
        model.addAttribute("boards", boardService.findAllDesc());
        return "index";
    }

    @GetMapping("/board/save")
    public String saveBord() {
        return "board-save";
    }

    @GetMapping("/board/update/{id}")
    public String updateBoard(@PathVariable Long id, Model model) {
        final BoardResponseDto dto = boardService.findById(id);
        model.addAttribute("board", dto);

        return "board-update";
    }
}
