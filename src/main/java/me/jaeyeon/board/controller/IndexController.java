package me.jaeyeon.board.controller;

import lombok.RequiredArgsConstructor;
import me.jaeyeon.board.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final BoardService boardService;

    @GetMapping("/")
    public String main(Model model) {
        model.addAttribute("boards", boardService.findAllDesc());
        return "index";
    }
}
