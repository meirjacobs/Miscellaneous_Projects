package my.cool.projects;

public class Bishop extends Piece {

    public Bishop(Color color, BoardLocation boardLocation) {
        super(color, boardLocation);
        pieceType = PieceType.BISHOP;
    }

    @Override
    public boolean validMove(Piece[][] board, int currentRow, int currentColumn, int moveToRow, int moveToColumn, boolean capture) {
        if(!validateInput(board, currentRow, currentColumn, moveToRow, moveToColumn)) return false;
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
        if(!(board[currentRow][currentColumn] instanceof Bishop)) {
            System.err.printf("Piece at current location is a %s, not a Bishop\n", board[currentRow][currentColumn].getClass());
            return false;
        }
        if(Math.abs(moveToRow - currentRow) != Math.abs(moveToColumn - currentColumn)) {
            System.err.println("Bishops must move diagonally");
            return false;
        }
        if(currentColumn == moveToColumn) {
            System.err.println("You have not moved the Bishop");
            return false;
        }
        int rowDirection;
        int columnDirection;
        int originalRow = currentRow;
        int originalColumn = currentColumn;
        rowDirection = (moveToRow - currentRow > 0) ? 1 : -1;
        columnDirection = (moveToColumn - currentColumn > 0) ? 1 : -1;
        currentRow += rowDirection;
        currentColumn += columnDirection;
        for(; currentRow != moveToRow && currentColumn != moveToColumn;) {
            if(board[currentRow][currentColumn] != null) {
                System.err.println("Cannot move Bishop because there is a piece in the way of the destination");
                return false;
            }
            currentRow += rowDirection;
            currentColumn += columnDirection;
        }
        return true;
    }
}