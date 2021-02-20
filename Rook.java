public class Rook extends Pieces{

    public Rook(int[] pos, int color) {
        this.position = pos;
        this.color = color;
        this.moves = 0;
    }


    @Override
    public moveType canMove(int[] move) {
        //Vertical Checks
        if(position[0] == move[0]) {
            //Checks Downward
            if(position[1] > move[1]) {
                for(int i = position[1] - 1; i >= move[1]; i--) {
                    if(SimpleBoard.board[move[0]][i] != null) {
                        if(move[1] == i) return moveType.Take;
                        return moveType.Cannot;
                    }
                }
                return moveType.Can;
            }

            //Checks Upward
            else if(position[1] < move[1]) {
                for(int i = position[1] + 1; i <= move[1]; i++) {
                    if(SimpleBoard.board[move[0]][i] != null) {
                        if(move[1] == i) return moveType.Take;
                        System.out.println(i);
                        return moveType.Cannot;
                    }
                }
                return moveType.Can;
            }
            return moveType.Cannot;
        }

        //Horizontal Checks
        if(position[1] == move[1]) {
            //Checks Left
            if(position[0] > move[0]) {
                for(int i = position[0] - 1; i >= move[0]; i--) {
                    if(SimpleBoard.board[i][move[1]] != null) {
                        if(move[0] == i) return moveType.Take;
                        return moveType.Cannot;
                    }
                }
                return moveType.Can;
            }

            //Checks Right
            else if(position[0] < move[0]) {
                    for(int i = position[0] + 1; i <= move[0]; i++) {
                        if(SimpleBoard.board[i][move[1]] != null) {
                            if(move[0] == i) return moveType.Take;
                            return moveType.Cannot;
                        }
                    }
                    return moveType.Can;
            }
            return moveType.Cannot;
        }
        return moveType.Cannot;
    }


    @Override
    public String getType() {
        return "Rook";
    }
}
