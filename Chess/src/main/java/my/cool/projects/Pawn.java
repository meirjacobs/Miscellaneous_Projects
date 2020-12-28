package my.cool.projects;

public class Pawn extends Piece {

    public Pawn(Color color, BoardLocation boardLocation) {
        super(color, boardLocation);
        pieceType = PieceType.PAWN;
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
        if(!(board[currentRow][currentColumn] instanceof Pawn)) {
            System.err.printf("Piece at current location is a %s, not a Pawn\n", board[currentRow][currentColumn].getClass());
            return false;
        }
        if(moveToColumn != currentColumn) {
            System.err.println("Pawns must move up-down or left-right");
            return false;
        }
        if(currentRow == moveToRow) {
            System.err.println("You have not moved the Pawn");
            return false;
        }
        if((currentColumn != 1 && this.color == Color.WHITE && moveToRow - currentRow != 1) || (currentColumn != 6 && this.color == Color.BLACK && moveToRow - currentRow != -1)) {
            System.err.println("Pawn must move one space forward");
            return false;
        }
        if((currentColumn == 1 && this.color == Color.WHITE && !(moveToRow - currentRow == 1 || moveToRow - currentRow == 2)) || ((currentColumn == 6 && this.color == Color.BLACK && !(moveToRow - currentRow == -1 || moveToRow - currentRow == -2)))) {
            System.err.println("Pawn must move one or two spaces forward");
            return false;
        }
        for(; currentRow <= moveToRow; currentRow++) {
            if (board[currentRow][currentColumn] != null) {
                System.err.println("Cannot move Pawn because there is a piece in the way of the destination");
                return false;
            }
        }
        return true;
    }
}