package my.cool.projects;

import java.util.*;

public class PlayChess {
    private static HashMap<Piece, Set<Integer>> piecesToSquares;
    private static HashMap<Integer, Set<Piece>> squaresToPieces;
    private static Piece[][] board;

    public static void main(String[] args) {
        initializeBoard();
        initPTS();
        initSTP();
        Scanner scanner = new Scanner(System.in);
        boolean whiteTurn = true;
        while(true) {
            String move = scanner.next();
            Piece.PieceType pieceType = determinePiece(move);
            boolean capture = determineCapture(move);
            int row  = determineMoveToRow(move);
            int column = determineMoveToColumn(move);
            if(pieceType == null || row == -1 || column == -1) {
                continue;
            }





            whiteTurn = !whiteTurn;
        }

    }

    private static int determineMoveToColumn(String move) {
        int column = move.charAt(move.length()-2) - 97;
        if(column < 0 || column > 7) {
            System.err.println((char)column + "is not a valid column");
            return -1;
        }
        return column;
    }

    private static int determineMoveToRow(String move) {
        int row = move.charAt(move.length()-2) - 97;
        if(row < 0 || row > 7) {
            System.err.println((char)row + "is not a valid row");
            return -1;
        }
        return row;
    }

    private static boolean determineCapture(String move) {
        for(int i = 1; i < move.length(); i++) {
            if(move.charAt(i) == 'x') {
                return true;
            }
        }
        return false;
    }

    private static Piece.PieceType determinePiece(String move) {
        switch (move.charAt(0)) {
            case 'a':
            case 'b':
            case 'c':
            case 'd':
            case 'e':
            case 'f':
            case 'g':
            case 'h':
                return Piece.PieceType.PAWN;
            case 'B':
                return Piece.PieceType.BISHOP;
            case 'N':
                return Piece.PieceType.KNIGHT;
            case 'R':
                return Piece.PieceType.ROOK;
            case 'Q':
                return Piece.PieceType.QUEEN;
            case 'K':
                return Piece.PieceType.KING;
            default:
                System.err.println(move.charAt(0) + " is not a valid piece");
                return null;
        }
    }

    private static void initializeBoard() {
        board = new Piece[8][8];
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
    }

    private static void initPTS() {
        int i = 1;
        for(int j = 0; j < 8; j++) {
            Set<Integer> set = new HashSet<>();
            set.add((i+1)*j);
            set.add((i+2)*j);
            PlayChess.piecesToSquares.put(board[i][j], set);
        }
        i = 6;
        for(int j = 0; j < 8; j++) {
            Set<Integer> set = new HashSet<>();
            set.add((i-1)*j);
            set.add((i-2)*j);
            PlayChess.piecesToSquares.put(board[i][j], set);
        }
        Set<Integer> set = new HashSet<>();
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        Set<Integer> set3 = new HashSet<>();
        set.add(toCoordinate("a3"));
        set.add(toCoordinate("c3"));
        PlayChess.piecesToSquares.put(board[0][1], set);
        set1.add(toCoordinate("f3"));
        set1.add(toCoordinate("h3"));
        PlayChess.piecesToSquares.put(board[0][6], set1);
        set2.add(toCoordinate("a6"));
        set2.add(toCoordinate("c6"));
        PlayChess.piecesToSquares.put(board[7][1], set2);
        set3.add(toCoordinate("f6"));
        set3.add(toCoordinate("h6"));
        PlayChess.piecesToSquares.put(board[7][6], set3);
    }

    private static void initSTP() {
        squaresToPieces = new HashMap<>();
        for(Piece piece : piecesToSquares.keySet()) {
            for(int square : piecesToSquares.get(piece)) {
                if(!squaresToPieces.containsKey(square)) {
                    Set<Piece> set = new HashSet<>();
                    set.add(piece);
                    squaresToPieces.put(square, set);
                }
                else {
                    Set<Piece> set = squaresToPieces.get(square);
                    set.add(piece);
                    squaresToPieces.put(square, set);
                }
            }
        }
    }

    private static int toCoordinate(String chessLingo) {
        int row = chessLingo.charAt(0) - 97;
        int col = chessLingo.charAt(1) - 49;
        return row * 8 + col - 1;
    }

}