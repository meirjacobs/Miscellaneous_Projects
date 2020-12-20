package my.cool.projects;

public class King extends Piece {

    public King(Color color) {
        super(color);
    }

    @Override
    public boolean validMove(Piece[][] board, int currentRow, int currentColumn, int moveToRow, int moveToColumn) {
        validateInput(board, currentRow, currentColumn, moveToRow, moveToColumn);
        if(board[currentRow][currentColumn] == null) {
            System.err.println("No piece at current location");
            return false;
        }
        if(board[moveToRow][moveToColumn] != null) {
            System.err.println("Move-to square is already occupied");
            return false;
        }
        //this method may not be necessary
        if(!(board[currentRow][currentColumn] instanceof King)) {
            System.err.printf("Piece at current location is a %s, not a King\n", board[currentRow][currentColumn].getClass());
            return false;
        }
        if(Math.max(Math.abs(moveToRow - currentRow), Math.abs(moveToColumn - currentColumn)) != 1) {
            System.err.println("King must move one square");
            return false;
        }
        /*board[moveToRow][moveToColumn] = board[currentRow][currentColumn];
        board[currentRow][currentColumn] = null;*/
        return true;
    }
}