package me.jaeyeon.board.service;

import lombok.RequiredArgsConstructor;
import me.jaeyeon.board.domain.posts.Posts;
import me.jaeyeon.board.domain.posts.PostsRepository;
import me.jaeyeon.board.web.dto.PostsListResponseDto;
import me.jaeyeon.board.web.dto.PostsResponseDto;
import me.jaeyeon.board.web.dto.PostsSaveRequestDto;
import me.jaeyeon.board.web.dto.PostsUpdateRequestDto;
import org.springframework.data.domain.*;
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
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = getPosts(postsRepository, id);

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts posts = getPosts(postsRepository, id);

        return new PostsResponseDto(posts);
    }

    public Page<PostsListResponseDto> findAllDesc(Pageable pageable) {
        Page<Posts> posts = postsRepository.findAllDesc(pageable);
        List<PostsListResponseDto> results = posts.getContent().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
        long totalCount = posts.getTotalElements();

        return new PageImpl<>(results, pageable, totalCount);
    }

    @Transactional
    public void delete(Long id) {
        Posts posts = getPosts(postsRepository, id);
        postsRepository.delete(posts);
    }

    private Posts getPosts(PostsRepository postsRepository, Long id) {
        Posts posts = postsRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        return posts;
    }
}
