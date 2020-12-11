package my.cool.projects;

public abstract class Piece {
    Color color;

    public Piece(Color color) {
        switch (color) {
            case WHITE:
            case BLACK:
                this.color = color;
            default:
                throw new IllegalArgumentException("Invalid piece color");
        }
    }

    public enum Color {WHITE, BLACK};

    public abstract boolean move(Piece[][] board, int currentRow, int currentColumn, int moveToRow, int moveToColumn);

    public void validateInput(Piece[][] board, int currentRow, int currentColumn, int moveToRow, int moveToColumn) {
        if(board == null) {
            System.err.println("Null board input");
        }
        if(board.length != 8 || board[0].length != 8) {
            System.err.println("Board must be 8 x 8");
        }
        if(currentRow < 0 || currentRow > 7 || currentColumn < 0 || currentColumn > 7 ||moveToRow < 0 || moveToRow > 7 || moveToColumn < 0 || moveToColumn > 7) {
            System.err.println("Invalid current or intended location input. Must be between 0 and 7");
        }
    }

}