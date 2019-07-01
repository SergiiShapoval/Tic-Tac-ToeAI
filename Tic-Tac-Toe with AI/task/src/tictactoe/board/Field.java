package tictactoe.board;

import tictactoe.GameResult;

import static tictactoe.GameResult.*;
import static tictactoe.board.Sign.BLANK;

public class Field {

    private final int row, column;
    private Sign sign = BLANK;

    public Field(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public boolean isOccupied() {
        return !sign.equals(BLANK);
    }

    void occupy(Sign sign) {
        this.sign = sign;
    }

    boolean hasSameSign(Field that) {
        if (that == null){
            return false;
        }
        return this.sign.equals(that.sign);
    }

    public boolean hasSameSign(Sign sign) {
        return this.sign.equals(sign);
    }

    GameResult whoWins(){
        switch (sign) {
            case X:
                return XWINS;
            case O:
                return OWINS;
        }
        return IN_PROGRESS;
    }

    String toSign() {
        return sign.toString();
    }

    @Override
    public String toString() {
        return "\nField{" +
                "row=" + row +
                ", column=" + column +
                ", sign=" + sign +
                "}\n";
    }
}
