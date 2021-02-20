public class SimpleBoard {
    public class CannotMoveException extends Exception {}

    static Pieces[][] board = new Pieces[8][8];
    int pointsBlack = 0;
    int pointsWhite = 0;
    int turn = 0;
    public void addPiece(Pieces piece) {
        int x = piece.position[0];
        int y = piece.position[1];
        board[x][y] = piece;
    }

    //Removes given piece and returns its point value.
    public int removePiece(Pieces piece) {
        if(piece.getType() != "King") {
            board[piece.position[0]][piece.position[1]] = null;
        }
        switch(piece.getType()){
            case "Pawn":
                return 1;
            case "Knight":
                return 3;
            case "Bishop":
                return 3;
            case "Rook":
                return 5;
            case "Queen":
                return 9;
            default:
                return 0;
        }
    }

    public void movePiece(int[] initial, int[] move) {
        board[move[0]][move[1]] = board[initial[0]][initial[1]];
        board[initial[0]][initial[1]] = null;
    }

    public static void printBoard() {
        for(int y = 7; y >= 0; y--){
            for(int x = 0; x < 8; x++){
                if(board[x][y] == null) System.out.print("-");
                else {
                    switch(board[x][y].getType()) {
                        case "Pawn":
                            System.out.print("P");
                            break;
                        case "Knight":
                            System.out.print("K");
                            break;
                        case "Bishop":
                            System.out.print("B");
                            break;
                        case "Rook":
                            System.out.print("R");
                            break;
                        case "Queen":
                            System.out.print("Q");
                            break;
                        case "King":
                            System.out.print("#");
                            break;
                    }
                }
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    public void makeBoard() {
        //White Pawns
        addPiece(new Pawn(new int[]{0,1}, 0));
        addPiece(new Pawn(new int[]{1,1}, 0));
        addPiece(new Pawn(new int[]{2,1}, 0));
        addPiece(new Pawn(new int[]{3,1}, 0));
        addPiece(new Pawn(new int[]{4,1}, 0));
        addPiece(new Pawn(new int[]{5,1}, 0));
        addPiece(new Pawn(new int[]{6,1}, 0));
        addPiece(new Pawn(new int[]{7,1}, 0));

        //Black Pawns
        addPiece(new Pawn(new int[]{0,6}, 1));
        addPiece(new Pawn(new int[]{1,6}, 1));
        addPiece(new Pawn(new int[]{2,6}, 1));
        addPiece(new Pawn(new int[]{3,6}, 1));
        addPiece(new Pawn(new int[]{4,6}, 1));
        addPiece(new Pawn(new int[]{5,6}, 1));
        addPiece(new Pawn(new int[]{6,6}, 1));
        addPiece(new Pawn(new int[]{7,6}, 1));

        //Rooks
        addPiece(new Rook(new int[]{0,0}, 0));
        addPiece(new Rook(new int[]{7,0}, 0));
        addPiece(new Rook(new int[]{0,7}, 1));
        addPiece(new Rook(new int[]{7,7}, 1));

        //Bishops
        addPiece(new Bishop(new int[]{2,0}, 0));
        addPiece(new Bishop(new int[]{5,0}, 0));
        addPiece(new Bishop(new int[]{2,7}, 1));
        addPiece(new Bishop(new int[]{5,7}, 1));

        //Knights
        addPiece(new Knight(new int[]{1,0}, 0));
        addPiece(new Knight(new int[]{6,0}, 0));
        addPiece(new Knight(new int[]{1,7}, 1));
        addPiece(new Knight(new int[]{6,7}, 1));

        //Kings
        addPiece(new King(new int[]{4,0}, 0));
        addPiece(new King(new int[]{4,7}, 1));

        //Queens
        addPiece(new Queen(new int[]{3,0}, 0));
        addPiece(new Queen(new int[]{3,7}, 1));
    }

    //*WIP*
    public void unifiedPieceMove(int[] initial, int[] move) throws CannotMoveException {
        switch(board[initial[0]][initial[1]].canMove(move)) {
            case Can:
                board[initial[0]][initial[1]].moves++;
                board[initial[0]][initial[1]].position = move;
                movePiece(initial, move);

                break;
            case Take:
                board[initial[0]][initial[1]].moves++;
                board[initial[0]][initial[1]].position = move;
                removePiece(board[move[0]][move[1]]);
                movePiece(initial, move);
                break;
            case Cannot:
                throw new CannotMoveException();
        }
    }

    public static void main(String[] args) {
        //TESTING
        SimpleBoard sample = new SimpleBoard();
        sample.makeBoard();
        try {
            sample.unifiedPieceMove(new int[]{0,1}, new int[]{0,3});
            sample.unifiedPieceMove(new int[]{0,0}, new int[]{0,2});
            sample.unifiedPieceMove(new int[]{0,2}, new int[]{7,2});
            sample.unifiedPieceMove(new int[]{7,2}, new int[]{7,6});
            sample.unifiedPieceMove(new int[]{7,6}, new int[]{6,6});
            sample.unifiedPieceMove(new int[]{0,3}, new int[]{0,4});
        }
        catch(Exception e) {
            System.out.println("can't move");
        }
        printBoard();

        /*
        try {
            System.out.println(board[2][1].canMove(new int[]{3, 2}));
        }
        catch(Pieces.PieceTakeException pte) {
            //Call remove function, and update position.
        }
         */


    }
}
