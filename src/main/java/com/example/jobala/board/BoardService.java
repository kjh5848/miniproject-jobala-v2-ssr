package com.example.jobala.board;

import com.example.jobala._core.errors.exception.Exception404;
import com.example.jobala._user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardJPARepository boardJPARepository;

    public Board 글상세보기(int boardId, User sessionUser){
        Board board = boardJPARepository.findByIdJoinUser(boardId)
                .orElseThrow(() ->  new Exception404("게시글을 찾을 수 없습니다"));

        boolean isOwner = false;
        if (sessionUser != null){
            if (sessionUser.getId() == board.getUser().getId()){
             isOwner = true;
            }
        }

        board.setOwner(isOwner);
        return board;

    }
}
