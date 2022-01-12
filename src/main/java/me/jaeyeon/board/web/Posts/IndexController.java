package me.jaeyeon.board.web.Posts;

import lombok.RequiredArgsConstructor;
import me.jaeyeon.board.domain.posts.Posts;
import me.jaeyeon.board.service.PostsService;
import me.jaeyeon.board.web.dto.PostsListResponseDto;
import me.jaeyeon.board.web.dto.PostsResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model, @PageableDefault Pageable pageable) {
//        model.addAttribute("posts", postsService.findAllDesc());

        pageable = PageRequest.of(pageable.getPageNumber(), 10);
        model.addAttribute("posts", postsService.findAllDesc(pageable));
        return "index";
    }

    @GetMapping("/posts-save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }
}
