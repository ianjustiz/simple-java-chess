public class Pawn extends Pieces{

    public Pawn(int[] pos, int color) {
        this.position = pos;
        this.color = color;
        this.moves = 0;
    }

    @Override
    public moveType canMove(int[] move) {
        //Check to the right (FOR WHITE ONLY?)
        if(color == 0) {
            if (position[0] == move[0]) {
                if (move[1] <= position[1] + 2) {
                    if (move[1] == position[1] + 2 && moves == 0 && (SimpleBoard.board[move[0]][move[1] + 1] == null && SimpleBoard.board[move[0]][move[1]] == null))
                        return moveType.Can;
                    if (move[1] == position[1] + 1 && SimpleBoard.board[move[0]][move[1]] == null) return moveType.Can;
                }
            }
            if((position[0] == move[0] + 1 || position[0] == move[0] - 1) && (position[1] == move[1] - 1)) {
                if(SimpleBoard.board[move[0]][move[1]].color == this.reverseColor()) {
                    return moveType.Take;
                }
            }
        } else {
            if (position[0] == move[0]) {
                if (move[1] >= position[1] - 2) {
                    if (move[1] == position[1] - 2 && moves == 0 && (SimpleBoard.board[move[0]][move[1] - 1] == null && SimpleBoard.board[move[0]][move[1]] == null))
                        return moveType.Can;
                    if (move[1] == position[1] - 1 && SimpleBoard.board[move[0]][move[1]] == null) return moveType.Can;
                }
            }
            if((position[0] == move[0] + 1 || position[0] == move[0] - 1) && (position[1] == move[1] + 1)) {
                if(SimpleBoard.board[move[0]][move[1]].color == this.reverseColor()) {
                    return moveType.Take;
                }
            }
        }
        return moveType.Cannot;
    }

    @Override
    public String getType() {
        return "Pawn";
    }
}
