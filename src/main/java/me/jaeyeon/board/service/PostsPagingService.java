package me.jaeyeon.board.service;

import lombok.RequiredArgsConstructor;
import me.jaeyeon.board.domain.posts.Posts;
import me.jaeyeon.board.domain.posts.PostsPagingRepository;
import me.jaeyeon.board.web.dto.PostsListResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostsPagingService {

    private final PostsPagingRepository postsPagingRepository;

    public Page<PostsListResponseDto> findAllDesc(Pageable pageable) {
        Page<Posts> posts = postsPagingRepository.findAllDesc(pageable);
        List<PostsListResponseDto> results = posts.getContent().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
        long totalCount = posts.getTotalElements();

        return new PageImpl<>(results, pageable, totalCount);
    }
}
