package my.cool.projects;

public class PlayChess {

    public static void main(String[] args) {
        Piece[][] board = initializeBoard();
    }

    private static Piece[][] initializeBoard() {
        Piece[][] board = new Piece[8][8];
        board[0][0] = new Rook(Piece.Color.WHITE);
        board[0][1] = new Knight(Piece.Color.WHITE);
        board[0][2] = new Bishop(Piece.Color.WHITE);
        board[0][3] = new Queen(Piece.Color.WHITE);
        board[0][4] = new King(Piece.Color.WHITE);
        board[0][5] = new Bishop(Piece.Color.WHITE);
        board[0][6] = new Knight(Piece.Color.WHITE);
        board[0][7] = new Rook(Piece.Color.WHITE);
        for(int i = 0; i < 8; i++) {
            board[1][i] = new Pawn(Piece.Color.WHITE);
        }
        board[7][0] = new Rook(Piece.Color.BLACK);
        board[7][1] = new Knight(Piece.Color.BLACK);
        board[7][2] = new Bishop(Piece.Color.BLACK);
        board[7][3] = new Queen(Piece.Color.BLACK);
        board[7][4] = new King(Piece.Color.BLACK);
        board[7][5] = new Bishop(Piece.Color.BLACK);
        board[7][6] = new Knight(Piece.Color.BLACK);
        board[7][7] = new Rook(Piece.Color.BLACK);
        for(int i = 0; i < 8; i++) {
            board[6][i] = new Pawn(Piece.Color.BLACK);
        }
        return board;
    }

}