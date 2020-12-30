package my.cool.projects;

public abstract class Piece {
    Color color;
    BoardLocation boardLocation;
    PieceType pieceType;

    public Piece(Color color, BoardLocation boardLocation) {
        switch (color) {
            case WHITE:
            case BLACK:
                this.color = color;
                break;
            default:
                throw new IllegalArgumentException("Invalid piece color");
        }
        this.boardLocation = boardLocation;
    }

    public enum PieceType {PAWN, KNIGHT, BISHOP, ROOK, QUEEN, KING};

    public enum Color {WHITE, BLACK};

    public abstract boolean validMove(Piece[][] board, int currentRow, int currentColumn, int moveToRow, int moveToColumn, boolean capture);

    protected boolean validateInput(Piece[][] board, int currentRow, int currentColumn, int moveToRow, int moveToColumn) {
        if(board == null) {
            System.err.println("Null board input");
            return false;
        }
        if(board.length != 9 || board[1].length != 9) {
            System.err.println("Board must be 9 x 9");
            return false;
        }
        if(currentRow < 1 || currentRow > 8 || currentColumn < 1 || currentColumn > 8 ||moveToRow < 1 || moveToRow > 8 || moveToColumn < 1 || moveToColumn > 8) {
            System.err.println("Invalid current or intended location input. Must be between 1 and 8");
            return false;
        }
        if(board[currentRow][currentColumn] == null) {
            System.err.println("No piece at current location");
            return false;
        }
        return true;
    }

}