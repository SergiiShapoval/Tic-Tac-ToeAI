package tictactoe.player;

import tictactoe.board.Board;
import tictactoe.board.Sign;

import java.util.Scanner;

public class User extends Player {

    private final Scanner scanner;

    User(Sign sign, Board board, Scanner scanner) {
        super(sign, board);
        this.scanner = scanner;
    }

    @Override
    public void move() {
        int leftRight = 0;
        int topBottom = 0;
        for (; ; ) {
            System.out.println("Enter the coordinates:");
            try {
                leftRight = Integer.valueOf(scanner.next());
                topBottom = Integer.valueOf(scanner.next());
            } catch (NumberFormatException e) {
                if (scanner.hasNextLine()) {
                    scanner.nextLine();
                }
                System.out.println("You should enter numbers!");
                continue;
            }
            if (invalidCoordinate(leftRight) || invalidCoordinate(topBottom)) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            }
            int row = translateTopBottom(topBottom, board.length());
            int column = translateLeftRight(leftRight);
            if (board.isOccupied(row, column)) {
                System.out.println("This cell is occupied! Choose another one!");
                continue;
            }
            board.addMove(row, column, playerSign());
            break;
        }
    }

    private boolean invalidCoordinate(int coordinate) {
        return coordinate > 3 || coordinate < 1;
    }

    private int translateTopBottom(int topBottom, int len) {
        return len - topBottom;
    }

    private int translateLeftRight(int x) {
        return x - 1;
    }

}
