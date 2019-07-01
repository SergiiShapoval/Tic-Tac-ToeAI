package tictactoe.board;

import tictactoe.GameResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import static tictactoe.GameResult.*;
import static tictactoe.GameResult.DRAW;

public class Line {

    private final List<Field> fields;

    Line(Field[] fields) {
        this.fields = Arrays.asList(fields);
    }

    public List<Field> fields() {
        return new ArrayList<>(fields);
    }

    public Field findFirstFree(){
        for (Field field : fields) {
            if (!field.isOccupied()){
                return field;
            }
        }
        return null;
    }

    private boolean hasFreeFields() {
        for (Field v : fields) {
            if (!v.isOccupied()) {
                return true;
            }
        }
        return false;
    }

    private boolean isAllEquals() {
        int cursor = -1;
        while (++cursor < fields.size() && fields.get(0).hasSameSign(fields.get(cursor))) {
        }
        return cursor == fields.size();
    }

    GameResult checkLine() {
        if (hasFreeFields()) {
            return IN_PROGRESS;
        }
        if (!isAllEquals()) {
            return DRAW;
        }
        return fields.get(0).whoWins();
    }

    void print() {
        System.out.print("| ");
        for (Field field : fields) {
            System.out.print(field.toSign() + " ");
        }
        System.out.println("|");
    }

    @Override
    public String toString() {
        return "Line{" +
                "fields=" + fields +
                '}';
    }
}
