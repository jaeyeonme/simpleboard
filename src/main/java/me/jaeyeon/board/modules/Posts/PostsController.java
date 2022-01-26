package me.jaeyeon.board.modules.Posts;

import lombok.RequiredArgsConstructor;
import me.jaeyeon.board.modules.Posts.form.PostsDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class PostsController {

    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model, @PageableDefault(sort = "id", direction = Sort.Direction.DESC)
            Pageable pageable) {

        model.addAttribute("posts", postsService.pageList(pageable));

        return "index";
    }

    @GetMapping("/posts/save")
    public String save() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String update(@PathVariable Long id, Model model) {
        final PostsDto.Response dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }
}
