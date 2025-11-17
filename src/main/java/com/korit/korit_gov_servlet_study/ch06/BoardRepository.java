package com.korit.korit_gov_servlet_study.ch06;

import java.util.ArrayList;
import java.util.List;

public class BoardRepository {
    private static BoardRepository instance;
    private List<Board> boards;
    private Long boardId = 1L;

    private BoardRepository() {
        boards = new ArrayList<>();
    }

    public static BoardRepository getInstance() {
        if (instance == null) {
            instance = new BoardRepository();
        }
        return instance;
    }

    public Board addBoard(Board board) {
        board.setBoardId(boardId++);
        boards.add(board);
        return board;
    }

    public List<Board> getBoardListAll() {
        return boards;
    }
}
