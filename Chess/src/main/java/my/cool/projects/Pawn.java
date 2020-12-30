package my.cool.projects;

public class Pawn extends Piece {

    public Pawn(Color color, BoardLocation boardLocation) {
        super(color, boardLocation);
        pieceType = PieceType.PAWN;
    }

    @Override
    public boolean validMove(Piece[][] board, int currentRow, int currentColumn, int moveToRow, int moveToColumn, boolean capture) {
        if(!validateInput(board, currentRow, currentColumn, moveToRow, moveToColumn)) return false;
        if(currentRow == moveToRow) {
            System.err.println("You have not moved the Pawn");
            return false;
        }
        if(!capture) {
            if(board[moveToRow][moveToColumn] != null) {
                System.err.println("Move-to square is already occupied");
                return false;
            }
            if(moveToColumn != currentColumn) {
                System.err.println("Pawns must move up-down");
                return false;
            }
            if((currentRow != 2 && this.color == Color.WHITE && moveToRow - currentRow != 1) || (currentColumn != 7 && this.color == Color.BLACK && moveToRow - currentRow != -1)) {
                System.err.println("Pawn must move one space forward");
                return false;
            }
            if((currentRow == 2 && this.color == Color.WHITE && !(moveToRow - currentRow == 1 || moveToRow - currentRow == 2)) || ((currentRow == 7 && this.color == Color.BLACK && !(moveToRow - currentRow == -1 || moveToRow - currentRow == -2)))) {
                System.err.println("Pawn must move one or two spaces forward");
                return false;
            }
            int inc;
            if (this.color == Color.WHITE) {
                currentRow++;
                inc = 1;
            } else {
                currentRow--;
                inc = -1;
            }
            for(; currentRow < moveToRow; currentRow += inc) {
                if (board[currentRow][currentColumn] != null) {
                    System.err.println("Cannot move Pawn because there is a piece in the way of the destination");
                    return false;
                }
            }
        }
        else {
            if(board[moveToRow][moveToColumn] == null) {
                System.err.println("There is no piece in the intended capture destination");
                return false;
            }
            if(board[moveToRow][moveToColumn].color.equals(board[currentRow][currentColumn].color)) {
                System.err.println("Cannot capture your own piece");
                return false;
            }
            if(this.color == Color.WHITE && !(moveToRow - currentRow == 1 && Math.abs(moveToColumn - currentColumn) == 1)) {
                System.err.println("Pawns must capture diagonally");
                return false;
            }
            else if(this.color == Color.BLACK && !(moveToRow - currentRow == -1 && Math.abs(moveToColumn - currentColumn) == 1)) {
                System.err.println("Pawns must capture diagonally");
                return false;
            }
        }
        return true;
    }
}