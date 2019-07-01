package tictactoe;

import tictactoe.board.Sign;

import static tictactoe.board.Sign.*;

public enum GameResult {
    DRAW,
    IN_PROGRESS,
    XWINS,
    OWINS,
    IMPOSSIBLE;

    @Override
    public String toString() {
        switch (this) {
            case DRAW:
                return "Draw";
            case IN_PROGRESS:
                return "Game not finished";
            case XWINS:
                return "X wins";
            case OWINS:
                return "O wins";
            case IMPOSSIBLE:
                return "Impossible";
            default:
                throw new IllegalArgumentException();
        }
    }
    public Sign winingSign(){
        if (this.equals(XWINS)){
            return X;
        }
        if (this.equals(OWINS)){
            return O;
        }
        return BLANK;
    }
}
