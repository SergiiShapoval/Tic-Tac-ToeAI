package tictactoe.player;

import tictactoe.board.Board;
import tictactoe.board.Field;
import tictactoe.board.Line;
import tictactoe.board.Sign;

import java.util.ArrayList;
import java.util.List;

public class MediumAI extends EasyAI {



    MediumAI(Sign sign, Board board, String level) {
        super(sign, board, level);
    }

    @Override
    public void move() {
        List<Line> winningLines = new ArrayList<>();
        List<Line> preventOpponentWinLines = new ArrayList<>();

        for (Line line :board.Lines()) {
            if (isWinningLine(line)){
                winningLines.add(line);
            }
            if (isPreventingLine(line)){
                preventOpponentWinLines.add(line);
            }
        }
        List<Line> allInterestedLines = new ArrayList<>();
        allInterestedLines.addAll(winningLines);
        allInterestedLines.addAll(preventOpponentWinLines);
        for (Line line : allInterestedLines) {
            Field mediumMove = line.findFirstFree();
            board.addMove(mediumMove.getRow(), mediumMove.getColumn(), playerSign());
            moveDeclaration();
            return;
        }
        super.move();
    }

    private boolean isPreventingLine(Line line) {
        int opponentFieldCounter = 0;
        for (Field field: line.fields()) {
            if (field.hasSameSign(playerSign().opponent())){
                opponentFieldCounter++;
                continue;
            }
            if (field.isOccupied()){
                return false;
            }
        }
        return opponentFieldCounter == board.length() - 1;
    }

    private boolean isWinningLine(Line line) {
        int ownFieldCounter = 0;
        for (Field field: line.fields()) {
            if (field.hasSameSign(playerSign())){
                ownFieldCounter++;
                continue;
            }
            if (field.isOccupied()){
                return false;
            }
        }
        return ownFieldCounter == board.length() - 1;
    }
}
