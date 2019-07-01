package tictactoe.board;

public enum Sign {
    X,
    O,
    BLANK;

    @Override
    public String toString() {
        if (!this.equals(BLANK)){
           return this.name();
        }
        return " ";
    }

    public Sign opponent(){
        if (this.equals(BLANK)){
            return this;
        }
        if (this.equals(X)){
            return O;
        }
        return X;
    }

}
