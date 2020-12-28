package my.cool.projects;

import java.util.*;

public class PlayChess {
    private static HashMap<Piece, Set<BoardLocation>> piecesToSquares;
    private static HashMap<BoardLocation, Set<Piece>> squaresToPieces;
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
            int toRow  = determineMoveToRow(move);
            int toColumn = determineMoveToColumn(move);
            if(pieceType == null || toRow == -1 || toColumn == -1) continue;
            Set<Piece> set = squaresToPieces.get(new BoardLocation(toRow, toColumn));
            if(set.isEmpty()) {
                System.err.printf("Piece cannot move to [%d, %d]\n", toRow, toColumn);
                continue;
            }
            Piece piece = identifyMovePiece(set, pieceType);
            if(piece == null) continue;
            if(!piece.validMove(board, piece.boardLocation.row, piece.boardLocation.column, toRow, toColumn, capture)) continue;
            BoardLocation moveTo = new BoardLocation(toRow, toColumn);
            boolean successful = capture ? capture(piece, moveTo) : move(piece, moveTo);
            if(!successful) continue;
            updateMaps();






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
        board[0][0] = new Rook(Piece.Color.WHITE, new BoardLocation(0,0));
        board[0][1] = new Knight(Piece.Color.WHITE, new BoardLocation(0,1));
        board[0][2] = new Bishop(Piece.Color.WHITE, new BoardLocation(0,2));
        board[0][3] = new Queen(Piece.Color.WHITE, new BoardLocation(0,3));
        board[0][4] = new King(Piece.Color.WHITE, new BoardLocation(0,4));
        board[0][5] = new Bishop(Piece.Color.WHITE, new BoardLocation(0,5));
        board[0][6] = new Knight(Piece.Color.WHITE, new BoardLocation(0,6));
        board[0][7] = new Rook(Piece.Color.WHITE, new BoardLocation(0,7));
        for(int i = 0; i < 8; i++) {
            board[1][i] = new Pawn(Piece.Color.WHITE, new BoardLocation(1,i));
        }
        board[7][0] = new Rook(Piece.Color.BLACK, new BoardLocation(7,0));
        board[7][1] = new Knight(Piece.Color.BLACK, new BoardLocation(7,1));
        board[7][2] = new Bishop(Piece.Color.BLACK, new BoardLocation(7,2));
        board[7][3] = new Queen(Piece.Color.BLACK, new BoardLocation(7,3));
        board[7][4] = new King(Piece.Color.BLACK, new BoardLocation(7,4));
        board[7][5] = new Bishop(Piece.Color.BLACK, new BoardLocation(7,5));
        board[7][6] = new Knight(Piece.Color.BLACK, new BoardLocation(7,6));
        board[7][7] = new Rook(Piece.Color.BLACK, new BoardLocation(7,7));
        for(int i = 0; i < 8; i++) {
            board[6][i] = new Pawn(Piece.Color.BLACK, new BoardLocation(6,i));
        }
    }

    private static void initPTS() {
        int i = 1;
        for(int j = 0; j < 8; j++) {
            Set<BoardLocation> set = new HashSet<>();
            set.add(new BoardLocation(i+1, j));
            set.add(new BoardLocation(i+2, j));
            PlayChess.piecesToSquares.put(board[i][j], set);
        }
        i = 6;
        for(int j = 0; j < 8; j++) {
            Set<BoardLocation> set = new HashSet<>();
            set.add(new BoardLocation(i-1, j));
            set.add(new BoardLocation(i-2, j));
            PlayChess.piecesToSquares.put(board[i][j], set);
        }
        Set<BoardLocation> set = new HashSet<>();
        Set<BoardLocation> set1 = new HashSet<>();
        Set<BoardLocation> set2 = new HashSet<>();
        Set<BoardLocation> set3 = new HashSet<>();
        set.add(new BoardLocation(getCoordinateRow("a3"), getCoordinateColumn("a3")));
        set.add(new BoardLocation(getCoordinateRow("c3"), getCoordinateColumn("c3")));
        PlayChess.piecesToSquares.put(board[0][1], set);
        set1.add(new BoardLocation(getCoordinateRow("f3"), getCoordinateColumn("f3")));
        set2.add(new BoardLocation(getCoordinateRow("h3"), getCoordinateColumn("h3")));
        PlayChess.piecesToSquares.put(board[0][6], set1);
        set2.add(new BoardLocation(getCoordinateRow("a6"), getCoordinateColumn("a6")));
        set2.add(new BoardLocation(getCoordinateRow("c6"), getCoordinateColumn("c6")));
        PlayChess.piecesToSquares.put(board[7][1], set2);
        set3.add(new BoardLocation(getCoordinateRow("f6"), getCoordinateColumn("f6")));
        set3.add(new BoardLocation(getCoordinateRow("h6"), getCoordinateColumn("h6")));
        PlayChess.piecesToSquares.put(board[7][6], set3);
    }

    private static void initSTP() {
        squaresToPieces = new HashMap<>();
        for(Piece piece : piecesToSquares.keySet()) {
            for(BoardLocation square : piecesToSquares.get(piece)) {
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

    private static int getCoordinateRow(String chessLingo) {
        return chessLingo.charAt(0) - 97;
    }

    private static int getCoordinateColumn(String chessLingo) {
        return chessLingo.charAt(1) - 49;
    }

    private static Piece identifyMovePiece(Set<Piece> set, Piece.PieceType type) {
        int nOfType = 0;
        Piece returnPiece = null;
        for(Piece piece : set) {
            if(piece.pieceType.equals(type)) {
                nOfType++;
                returnPiece = piece;
            }
        }
        if(nOfType == 0) {
            System.err.printf("There is no %s that can be moved to the desired square\n", type.toString());
        }
        else if(nOfType > 1) {
            System.err.printf("There is more than one %s that can be moved to the desired square\nPlease specify which in your move syntax\n", type.toString());
        }
        return nOfType == 1 ? returnPiece : null;
    }

    private static boolean move(Piece piece, BoardLocation destination) {
        board[piece.boardLocation.row][piece.boardLocation.column] = null;
        board[destination.row][destination.column] = piece;
        piece.boardLocation = destination;
        return true;
    }

    private static boolean capture(Piece piece, BoardLocation destination) {
        Piece temp = board[destination.row][destination.column];
        move(piece, destination);
        piecesToSquares.remove(temp);
        for(BoardLocation boardLocation : squaresToPieces.keySet()) {
            Set<Piece> set = squaresToPieces.get(boardLocation);
            if(set.remove(temp)) squaresToPieces.put(boardLocation, set);
        }
        return true;
    }

    private static void updateMaps() {
        // go through every piece and go through all of it's potential valid squares and if it's valid add it to maps
        for(Piece piece : piecesToSquares.keySet()) {
            Set<BoardLocation> set = piecesToSquares.get(piece);
            set.clear();
            int currentRow = piece.boardLocation.row;
            int currentColumn = piece.boardLocation.column;
            for(int i = 0; i < 8; i++) {
                for(int j = 0; j < 8; j++) {
                    if(piece.validMove(board, currentRow, currentColumn, i, j, true) || piece.validMove(board, currentRow, currentColumn, i, j, false)) {
                        set.add(new BoardLocation(i, j));
                    }
                }
            }
            piecesToSquares.put(piece, set);
        }
        for(BoardLocation boardLocation : squaresToPieces.keySet()) {
            Set<Piece> set0 = squaresToPieces.get(boardLocation);
            set0.clear();
            squaresToPieces.put(boardLocation, set0);
        }
        for(Piece piece : piecesToSquares.keySet()) {
            for(BoardLocation square : piecesToSquares.get(piece)) {
                Set<Piece> set = squaresToPieces.get(square);
                set.add(piece);
                squaresToPieces.put(square, set);
            }
        }
    }

}