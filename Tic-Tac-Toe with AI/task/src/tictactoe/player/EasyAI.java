package tictactoe.player;

import tictactoe.board.Board;
import tictactoe.board.Field;
import tictactoe.board.Sign;

import java.util.List;
import java.util.Random;

public class EasyAI extends Player {

    private static final Random random = new Random(100000);

    private final String level;

    EasyAI(Sign sign, Board board, String level) {
        super(sign, board);
        this.level = level;
    }

    @Override
    public void move() {
        moveDeclaration();
        List<Field> moves = board.availableMoves();
        Field possibleField = moves.get(random.nextInt(moves.size()));
        board.addMove(possibleField.getRow(), possibleField.getColumn(), playerSign());
    }

    protected void moveDeclaration() {
        System.out.printf("Making move level \"%s\"\n", level);
    }


}
