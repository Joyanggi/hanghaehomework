package com.example.hanghaehomework.controller;


import com.example.hanghaehomework.dto.BoardRequestDto;
import com.example.hanghaehomework.dto.BoardResponseDto;
import com.example.hanghaehomework.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    //게시글 작성
    @PostMapping("/board")
    public BoardResponseDto creatBoard(@RequestBody BoardRequestDto requestDto) {
        return boardService.createBoard(requestDto);
    }

    //게시글 조회
    @GetMapping("/board")
    public List<BoardResponseDto> getList() {
        return boardService.getList();
    }

    //선택한 게시글 조회
    @GetMapping("/board/{id}")
    public BoardResponseDto getBoard(@PathVariable Long id){
        return boardService.getBoard(id);
    }

    //선택한 게시글 수정
    @PutMapping("/board/{id}")
    public BoardResponseDto updateBoard(@PathVariable Long id, @RequestBody BoardRequestDto requestDto){
        return boardService.update(id, requestDto);
    }

    //게시글 삭제
    @DeleteMapping("/board/{id}")
    public String deleteBoard(@PathVariable Long id, @RequestBody BoardRequestDto password){
        return boardService.deleteBoard(id, password.getPassword());
    }
}