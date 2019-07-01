package tictactoe.board;

import tictactoe.GameResult;

import java.util.ArrayList;
import java.util.List;

import static tictactoe.GameResult.*;

public class Board {
    private final Field[][] matrix = new Field[3][3];
    private final List<Line> lines;
    private final List<Line> horizontalLines;

    public Board() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                matrix[i][j]= new Field(i,j);
            }
        }
        lines = collectAllLines();
        horizontalLines = collectLinesToPrint();
    }

    public void print(){
            printSeparator();
            for (Line line : horizontalLines) {
                line.print();
            }
            printSeparator();
    }

    private void printSeparator() {
        System.out.println("---------");
    }

    public void addMove(int row, int column, Sign sign){
        matrix[row][column].occupy(sign);
    }

    public int length(){
        return matrix.length;
    }

    public boolean isOccupied(int row, int column){
        return matrix[row][column].isOccupied();
    }

    public GameResult check(){
        if (!isValidMatrixByQty(matrix)) {
            return IMPOSSIBLE;
        }
        GameResult res = DRAW;
        for (Line line : lines) {
            res = considerLineInAllResults(res, line);
            if (res.equals(IMPOSSIBLE)) {
                return res;
            }
        }
        return res;
    }

    private boolean isValidMatrixByQty(Field[][] matrix) {
        int xCount = 0;
        int oCount = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                switch (matrix[i][j].whoWins()) {
                    case XWINS:
                        xCount++;
                        break;
                    case OWINS:
                        oCount++;
                        break;
                }
            }
        }
        return xCount - oCount <= 1 && oCount - xCount <= 1;
    }

    private GameResult considerLineInAllResults(GameResult res, Line line) {
        GameResult lineRes = line.checkLine();
        if (lineRes.equals(XWINS) || lineRes.equals(OWINS)) {
            if (res.ordinal() >= XWINS.ordinal() && !res.equals(lineRes)) {
                res = IMPOSSIBLE;
            }
        }
        if (res.ordinal() < lineRes.ordinal()) {
            res = lineRes;
        }
        return res;
    }

    public List<Field> availableMoves() {
        List<Field> fields = new ArrayList<>(matrix.length * matrix.length);
        for (Field[] row : matrix) {
            for (int j = 0; j < row.length; j++) {
                if (!row[j].isOccupied()) {
                    fields.add(row[j]);
                }
            }
        }
        return fields;
    }

    private List<Line> collectAllLines() {
        List<Line> lines = new ArrayList<>(2 * matrix.length + 2);
        lines.addAll(collectLinesToPrint());

//        collect columns
        for (int i = 0; i < matrix.length; i++) {
            Field[] column = new Field[matrix.length];
            for (int j = 0; j < matrix.length; j++) {
                column[j] = matrix[j][i];
            }
            lines.add(new Line(column));
        }

//        collect diagonals
        Field[] leftTopRightBottom = new Field[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            leftTopRightBottom[i] = matrix[i][i];
        }
        lines.add(new Line(leftTopRightBottom));

        Field[] leftBottomRightTop = new Field[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            leftBottomRightTop[i] = matrix[matrix.length - i - 1][i];
        }
        lines.add(new Line(leftBottomRightTop));

        return lines;
    }

    /**
     * @return horizontal lines
     */
    private List<Line> collectLinesToPrint() {
        List<Line> lines = new ArrayList<>(matrix.length);
        for (Field[] fields : matrix) {
            lines.add(new Line(fields));
        }
        return lines;
    }





    public List<Line> Lines() {
        return new ArrayList<>(lines);
    }
}
