package tictactoe;

import tictactoe.board.Board;
import tictactoe.player.*;

import java.io.IOException;
import java.util.Scanner;

import static tictactoe.GameResult.*;
import static tictactoe.board.Sign.O;
import static tictactoe.board.Sign.X;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        for (; ; ) {
            Board board = new Board();
            Player[] participants = setupPlayers(scanner, board);
            if (participants.length == 0) {
                break;
            }
            board.print();
            int step = 0;
            while (board.check().equals(IN_PROGRESS)) {
                Player participant = participants[step++ % 2];
                participant.move();
                board.print();
            }
            System.out.println(board.check());
        }
    }

    private static Player[] setupPlayers(Scanner scanner, Board board) {
        PlayerType first;
        PlayerType second;

        for (; ; ) {
            System.out.println("Input command:");
            String startCommand = scanner.nextLine();
            if (startCommand.equals("exit")) {
                return new Player[]{};
            }
            String[] commandArgs = startCommand.split(" ");
            if (commandArgs.length != 3) {
                System.out.println("Bad parameters!");
                continue;
            }
            if (!commandArgs[0].equals("start")) {
                System.out.println("Bad parameters!");
                continue;
            }
//            ugly but minimum efforts
            try {
                first = PlayerType.valueOf(commandArgs[1]);
                second = PlayerType.valueOf(commandArgs[2]);
            } catch (IllegalArgumentException e) {
                System.out.println("Bad parameters!");
                continue;
            }
            break;
        }
        PlayerFactory playerFactory = new PlayerFactory(scanner, board);

        return new Player[]{
                playerFactory.create(first, X),
                playerFactory.create(second, O),
        };
    }

    static String readInitialState(Scanner scanner) {
        System.out.println("Enter cells:");
        String line = scanner.nextLine();
        if (line.length() <= 2) {
            return null;
        }
        line = cleanFromSurroundingParentheses(line);
        if (line.length() != 9) {
            return null;
        }
        return line;
    }

    /**
     * @param line input program line
     * @return matrix field with values - 0
     */
    static int[][] matrix(String line) {
        int[][] matrix = new int[3][3];
        int counter = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                switch (line.charAt(counter++)) {
                    case 'X':
                        matrix[i][j] = 1;
                        break;
                    case 'O':
                        matrix[i][j] = 2;
                        break;
                }
            }
        }
        return matrix;
    }

    protected static String cleanFromSurroundingParentheses(String line) {
        line = line.substring(1, line.length() - 1);
        return line;
    }
}
