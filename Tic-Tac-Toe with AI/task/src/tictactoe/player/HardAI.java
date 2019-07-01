package tictactoe.player;

import tictactoe.board.Board;
import tictactoe.board.Field;
import tictactoe.board.Sign;

import java.util.ArrayList;
import java.util.List;

import static tictactoe.board.Sign.BLANK;

public class HardAI extends MediumAI {

    HardAI(Sign sign, Board board, String level) {
        super(sign, board, level);
    }

    static class Move {
        private final Field field;
        private int score;



        Move(Field field) {
            this.field = field;
        }

        Move(Field field, int score) {
            this.field = field;
            this.score = score;
        }

        public int getRow() {
            return field.getRow();
        }

        public int getColumn() {
            return field.getColumn();
        }

        @Override
        public String toString() {
            return "Move{" +
                    "field=" + field +
                    ", score=" + score +
                    '}';
        }
    }

    @Override
    public void move() {
        moveDeclaration();
        Move bestMove = miniMax(board, playerSign(), playerSign());
        board.addMove(bestMove.getRow(), bestMove.getColumn(), playerSign());
    }

    static Move miniMax(Board board, Sign playerSign, Sign turnSign) {
        List<Field> availableSpots = board.availableMoves();
        Move winLoseHappen = winLoseHappen(board, playerSign);
        if (winLoseHappen.score != 0) {
            return winLoseHappen;
        }
        if (availableSpots.size() == 0) {
            return new Move(null, 0);
        }

        List<Move> availableMoves = new ArrayList<>();
        for (Field spot : availableSpots) {
            Move iterationMove = new Move(spot);
//            emulate move
            board.addMove(spot.getRow(), spot.getColumn(), turnSign);
            if (playerSign.equals(turnSign)) {
                iterationMove.score = miniMax(board, playerSign, playerSign.opponent()).score;
            } else {
                iterationMove.score = miniMax(board, playerSign, playerSign).score;
            }
            availableMoves.add(iterationMove);
//            reset emulation move
            board.addMove(spot.getRow(), spot.getColumn(), BLANK);
        }
        return chooseBest(playerSign, turnSign, availableMoves);
    }

    static Move chooseBest(Sign playerSign, Sign turnSign, List<Move> availableMoves) {
        Move bestMove = null;
        if (playerSign.equals(turnSign)) {
            int bestScore = -100;
            for (Move move : availableMoves) {
                if (move.score > bestScore) {
                    bestScore = move.score;
                    bestMove = move;
                }
            }
        } else {
            int bestScore = 100;
            for (Move move : availableMoves) {
                if (move.score < bestScore) {
                    bestScore = move.score;
                    bestMove = move;
                }
            }
        }
        return bestMove;
    }


    static Move winLoseHappen(Board board, Sign playerSign) {
        final Sign winingSign = board.check().winingSign();
        if (winingSign.equals(playerSign)) {
            return new Move(null, 10);
        }
        if (winingSign.equals(playerSign.opponent())) {
            return new Move(null, -10);
        }
        return new Move(null);
    }
}
