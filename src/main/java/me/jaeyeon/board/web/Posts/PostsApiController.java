package me.jaeyeon.board.web.Posts;

import lombok.RequiredArgsConstructor;
import me.jaeyeon.board.service.PostsService;
import me.jaeyeon.board.web.dto.PostsResponseDto;
import me.jaeyeon.board.web.dto.PostsSaveRequestDto;
import me.jaeyeon.board.web.dto.PostsUpdateRequestDto;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.hateoas.MediaTypes;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@EnableJpaAuditing
@RequiredArgsConstructor
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping(produces = "/api/v1/posts", consumes = MediaTypes.HAL_FORMS_JSON_VALUE)
    public Long save(@RequestBody @Valid PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }

    @PutMapping(value = "/api/v1/posts/{id}", produces = MediaTypes.HAL_FORMS_JSON_VALUE, consumes = MediaTypes.HAL_FORMS_JSON_VALUE)
    public Long update(@PathVariable Long id, @RequestBody @Valid PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    @GetMapping(value = "/api/v1/posts/{id}", produces = MediaTypes.HAL_FORMS_JSON_VALUE)
    public PostsResponseDto findById(@PathVariable Long id)  {
        return postsService.findById(id);
    }

    @DeleteMapping(value = "/api/v1/posts/{id}", produces = MediaTypes.HAL_FORMS_JSON_VALUE)
    public Long delete(@PathVariable Long id) {
        postsService.delete(id);
        return id;
    }
}
