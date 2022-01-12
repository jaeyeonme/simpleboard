package me.jaeyeon.board.web.Posts;

import lombok.RequiredArgsConstructor;
import me.jaeyeon.board.service.PostsService;
import me.jaeyeon.board.web.dto.PostsResponseDto;
import me.jaeyeon.board.web.dto.PostsSaveRequestDto;
import me.jaeyeon.board.web.dto.PostsUpdateRequestDto;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableJpaAuditing
@RequiredArgsConstructor
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id)  {
        return postsService.findById(id);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id) {
        postsService.delete(id);
        return id;
    }
}
