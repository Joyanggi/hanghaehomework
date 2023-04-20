package com.example.hanghaehomework.service;

import com.example.hanghaehomework.dto.BoardRequestDto;
import com.example.hanghaehomework.dto.BoardResponseDto;
import com.example.hanghaehomework.entity.Board;
import com.example.hanghaehomework.entity.Member;
import com.example.hanghaehomework.jwt.JwtUtil;
import com.example.hanghaehomework.repository.BoardRepository;
import com.example.hanghaehomework.repository.MemberRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {
    public final BoardRepository boardRepository ;
    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;


    //게시글 작성
    public BoardResponseDto createBoard(BoardRequestDto requestDto, HttpServletRequest request) {
        Member member = checkJwtToken(request);


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
    public  BoardResponseDto update(Long id, BoardRequestDto requestDto, HttpServletRequest request) {
        Member member = checkJwtToken(request);

        Board board =boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );

        board.update(requestDto);
        return new BoardResponseDto(board);
    }

    //게시글 삭제
    public  String deleteBoard(Long id, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);

        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );
        boardRepository.deleteById(id);

        return "게시글 삭제 성공.";
    }

    public Member checkJwtToken(HttpServletRequest request){
        String token = jwtUtil.resolveToken(request);
        Claims claims;
        if(token != null){
            if(jwtUtil.validateToken(token)){
                claims = jwtUtil.getUserInfoFromToken(token);
            }else{
                throw new IllegalArgumentException("Token Error");
            }

            Member member = memberRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다")
            );
            return member;
        }
        return null;
    }
}