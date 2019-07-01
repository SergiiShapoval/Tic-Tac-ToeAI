package tictactoe.player;

import tictactoe.board.Board;
import tictactoe.board.Sign;

import java.util.Scanner;

public class PlayerFactory {

    private final Scanner scanner;
    private final Board board;

    public PlayerFactory(Scanner scanner, Board board) {
        this.scanner = scanner;
        this.board = board;
    }

    public Player create(PlayerType type, Sign sign) {
        switch (type) {
            case easy:
                return new EasyAI(sign, board, type.name());
            case medium:
                return new MediumAI(sign, board, type.name());
            case hard:
                return new HardAI(sign, board, type.name());
            case user:
                return new User(sign, board, scanner);
            default:
                throw new IllegalArgumentException(type.name());
        }
    }
}
