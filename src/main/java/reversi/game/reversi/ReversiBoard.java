package reversi.game.reversi;


/**
 * Represents a reversi board state;
 * bumb class, does not understand the game rules;
 * 1 stands for white
 * -1 stands for black
 * 0 stands for empty
 * @author Valhe Kouneli
 */
public class ReversiBoard {
    
    private final int[][] board; //board state
    
    public ReversiBoard() {
        board = new int[8][8];
        //setting the starting position
        board[3][3] = 1;
        board[4][4] = 1;
        board[3][4] = -1;
        board[4][3] = -1;
    }
    
    /**
     * Returns the state of the specified place on the board
     * @param x is row number stating from 0
     * @param y is column number stating from 0
     * @return 1 for white, -1 for black, 0 for empty
     * @throws ArrayIndexOutOfBoundsException if the specified place is not on the board
     */
    public int getBoardXY(int x, int y) {
        try {
            return board[x][y];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new java.lang.IllegalArgumentException(
                    "Bad coordinates.");
        }
    }
    
    /**
     * Sets a piece on the board
     * @param x is row number stating from 0
     * @param y is columns number starting from 0
     * @param value 1 for white, -1 for black, 0 for empty
     * @throws IllegalArgumentException if value is not 0, 1, or -1 or if the specified place is not on the board
     */
    public void setBoardXY(int x, int y, int value) {
        if (value != 1 && value != -1 && value != 0) {
            throw new java.lang.IllegalArgumentException(
                    "Only values 0, 1, and -1 allowed.");
        }
        try {
            board[x][y] = value;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new java.lang.IllegalArgumentException(
                    "Bad coordinates.");
        }
        
    }
    
    
    @Override
    public String toString() {
        String string = "   0 1 2 3 4 5 6 7 \n";
        String help;
        for(int i=0; i<8; i++) {
            string += i + " ";
            for(int j=0; j<8; j++) {
                string += "|";
                switch (board[i][j]) {
                    case -1:
                        help = "\u25CF"; //black circle
                        break;
                    case 1:
                        help = "\u25CB"; //white circle
                        break; 
                    default:
                        help = " ";
                        break;
                }
                string += help;
            }
            string += "|\n";
        }
        
        return string;
    }
    
    public ReversiBoard getCopy() {
        ReversiBoard copy = new ReversiBoard();
        for (int i=0; i<8; i++) {
            for (int j=0; j<8; j++) {
                copy.setBoardXY(i, j, board[i][j]);
            }
        }
        return copy;
    }
    
}
