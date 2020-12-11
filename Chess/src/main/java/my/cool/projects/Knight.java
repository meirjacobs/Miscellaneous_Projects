package my.cool.projects;

public class Knight extends Piece {

    public Knight(Color color) {
        super(color);
    }

    @Override
    public boolean move(Piece[][] board, int currentRow, int currentColumn, int moveToRow, int moveToColumn) {
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
        board[moveToRow][moveToColumn] = board[currentRow][currentColumn];
        board[currentRow][currentColumn] = null;
        return true;
    }
}