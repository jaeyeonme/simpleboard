package me.jaeyeon.board.modules.Posts;

import lombok.RequiredArgsConstructor;
import me.jaeyeon.board.modules.Posts.form.PostsDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsDto.Request dto) {
        return postsRepository.save(dto.toEntity()).getId();
    }

    public PostsDto.Response findById(Long id) {
        final Posts posts = getOne(id);

        return new PostsDto.Response(posts);
    }

    @Transactional
    public Long update(Long id, PostsDto.Request dto) {
        final Posts posts = getOne(id);

        posts.update(dto.getTitle(), dto.getContent());
        return id;
    }

    @Transactional
    public void delete(Long id) {
        final Posts posts = getOne(id);

        postsRepository.delete(posts);
    }

    public List<PostsDto.Response> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsDto.Response::new)
                .collect(Collectors.toList());
    }

    public Page<Posts> pageList(Pageable pageable) {
        return postsRepository.findAll(pageable);
    }

    private Posts getOne(Long id) {
        return postsRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
    }
}
