package me.jaeyeon.board.service;

import lombok.RequiredArgsConstructor;
import me.jaeyeon.board.dto.BoardListResponseDto;
import me.jaeyeon.board.dto.BoardSaveRequestDto;
import me.jaeyeon.board.dto.BoardUpdateRequestDto;
import me.jaeyeon.board.entity.Board;
import me.jaeyeon.board.repository.BoardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public Long save(BoardSaveRequestDto boardSaveRequestDto) {
        return boardRepository.save(boardSaveRequestDto.toEntity()).getId();
    }

    public List<BoardListResponseDto> findAllDesc() {
        return boardRepository.findAllDesc().stream()
                .map(BoardListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long update(Long id, BoardUpdateRequestDto boardUpdateRequestDto) {
        final Board board = getBoard(id);

        board.update(boardUpdateRequestDto.getTitle(), boardUpdateRequestDto.getContent());
        return id;
    }

    @Transactional
    public void delete(Long id) {
        final Board board = getBoard(id);

        boardRepository.delete(board);
    }

    private Board getBoard(Long id) {
        return boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시물이 없습니다. id= " + id));
    }
}
