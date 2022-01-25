package me.jaeyeon.board.modules.Posts;

import lombok.RequiredArgsConstructor;
import me.jaeyeon.board.modules.Posts.form.PostsDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PostsApiController {

    private final PostsService postsService;

    /**
     * Create
     */
    @PostMapping("/api/v1/posts")
    public ResponseEntity save(@RequestBody PostsDto.Request dto) {
        return ResponseEntity.ok(postsService.save(dto));
    }

    /**
     * Update
     */
    @PutMapping("/api/v1/posts/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody PostsDto.Request dto) {
        postsService.update(id, dto);
        return ResponseEntity.ok(id);
    }

    /**
     * Read
     */
    @GetMapping("/api/v1/posts/{id}")
    public ResponseEntity read(@PathVariable Long id) {
        return ResponseEntity.ok(postsService.findById(id));
    }

    /**
     * Delete
     */
    @DeleteMapping("/api/v1/posts/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        postsService.delete(id);
        return ResponseEntity.ok(id);
    }
}
