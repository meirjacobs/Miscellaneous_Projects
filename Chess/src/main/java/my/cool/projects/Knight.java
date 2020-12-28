package my.cool.projects;

public class Knight extends Piece {

    public Knight(Color color, BoardLocation boardLocation) {
        super(color, boardLocation);
        pieceType = PieceType.KNIGHT;
    }

    @Override
    public boolean validMove(Piece[][] board, int currentRow, int currentColumn, int moveToRow, int moveToColumn, boolean capture) {
        validateInput(board, currentRow, currentColumn, moveToRow, moveToColumn);
        if(board[currentRow][currentColumn] == null) {
            System.err.println("No piece at current location");
            return false;
        }
        if(!capture) {
            if(board[moveToRow][moveToColumn] != null) {
                System.err.println("Move-to square is already occupied");
                return false;
            }
        }
        else {
            if(board[moveToRow][moveToColumn] != null && board[moveToRow][moveToColumn].color.equals(board[currentRow][currentColumn].color)) {
                System.err.println("Cannot capture your own piece");
                return false;
            }
        }
        //this method may not be necessary
        if(!(board[currentRow][currentColumn] instanceof Knight)) {
            System.err.printf("Piece at current location is a %s, not a Knight\n", board[currentRow][currentColumn].getClass());
            return false;
        }
        int deltaX = moveToRow - currentRow;
        int deltaY = moveToColumn - currentColumn;
        if(!((Math.abs(deltaX) == 2 || Math.abs(deltaX) == 1) && (Math.abs(deltaY) == 2 || Math.abs(deltaY) == 1)) || Math.abs(deltaX) == Math.abs(deltaY)) {
            System.err.println("Knights must move 2 in one direction and 1 in the other");
            return false;
        }
        return true;
    }
}