package tictactoe.player;

import tictactoe.board.Board;
import tictactoe.board.Sign;

public abstract class Player {
    private final Sign sign;
    protected final Board board;

    public Player(Sign sign, Board board) {
        this.sign = sign;
        this.board = board;
    }

    Sign playerSign() {
        return sign;
    }

    public abstract void move();
}
