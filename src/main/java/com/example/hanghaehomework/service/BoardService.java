package com.example.hanghaehomework.service;

import com.example.hanghaehomework.dto.BoardRequestDto;
import com.example.hanghaehomework.dto.BoardResponseDto;
import com.example.hanghaehomework.entity.Board;
import com.example.hanghaehomework.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {
    public final BoardRepository boardRepository ;


    //게시글 작성
    public BoardResponseDto createBoard(BoardRequestDto requestDto) {
        Board board = new Board(requestDto);
        return new BoardResponseDto(boardRepository.save(board));
    }

    //게시글 목록 조회
    @Transactional(readOnly = true)
    public List<BoardResponseDto> getList() {
        return boardRepository.findAllByOrderByCreatedAtDesc().stream()
                .map(BoardResponseDto::new).collect(Collectors.toList());
    }

    //선택한 게시글 조회
    @Transactional(readOnly = true)
    public BoardResponseDto getBoard(Long id){
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 틀립니다.")
        );
        return new BoardResponseDto(board);
    }

    //게시글 수정
    public  BoardResponseDto update(Long id, BoardRequestDto requestDto) {
        Board board =boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("게시글이 수정되었습니다.")
        );
        if(requestDto.getPassword().equals(board.getPassword())){
            board.update(requestDto);
        } else {
            return new BoardResponseDto();
        }
        return new BoardResponseDto(board);
    }

    //게시글 삭제
    public  String deleteBoard(Long id, String password) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );
        if(password.equals(board.getPassword())){
            boardRepository.deleteById(id);
        } else {
            return "비밀번호가 틀립니다.";
        }
        return "게시글이 삭제되었습니다.";
    }
}