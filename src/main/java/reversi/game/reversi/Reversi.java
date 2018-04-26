package reversi.game.reversi;

import reversi.game.Game;
import reversi.data_structures.IntPair;
import reversi.data_structures.List;

/**
 *
 * @author Valhe Kouneli
 */
public class Reversi implements Game {
    
 
    
    private ReversiBoard board;
    private int turn; //black = 1, white = -1
    private int turnNumber;
    private List<Object> getMovesCache;
    private int lastTurnToAskMoves;
    private boolean gameIsOverCache;
    private int lastTurnToAskGameIsOver;
    
    public Reversi() {
        board = new ReversiBoard();
        turn = -1;
        turnNumber = 0;
        lastTurnToAskMoves = -1;
        getMovesCache = null;
        gameIsOverCache = false;
        lastTurnToAskGameIsOver = -1;
    }
    
    public void setBoard(ReversiBoard board) {
        this.board = board;
        int piecesOnBoard = 0;
        for (int i=0; i<8; i++) {
            for (int j=0; j<8; j++) {
                if (board.getBoardXY(i, j) != 0) {
                    piecesOnBoard++;
                }
            }
        }
        turnNumber = piecesOnBoard;
        turn = turnNumber % 2 == 0 ? -1 : 1;
    }
    
    @Override
    public int getTurn() {
        return turn;
    }
    
    @Override
    public int getTurnNumber() {
        return turnNumber;
    }
    
    
    /**
     * Returns a negative number if black is winning
     * and positive if white is winning.
     * @return (-1)*(number of black pieces)+(number of white pieces)
     */
    public int getScore() {
        int score = 0;
        for (int i=0; i<8; i++) {
            for (int j=0; j<8; j++) {
                score += board.getBoardXY(i, j);
            }
        }
        return score;
    }
    
    public int getWinner() {
        if (getScore() > 0) {
            return 1;
        } else if (getScore() < 0 ){
            return -1;
        } else {
            return 0;
        }
    }
    
    /**
     * Returns whether game is over or not.
     * According to the Reversi rules, game is over if both players do not have
     * any legal moves left.
     * @return  is this game over
     */
    @Override
    public boolean gameIsOver() {
        if (lastTurnToAskGameIsOver == turnNumber) {
            return gameIsOverCache;
        }
        boolean currentPlayerCanNotMove = getMoves().isEmpty();
        turn *= -1; //change turn temporarily
        turnNumber++; //change turn number temporarily
        boolean nextPlayerCanNotMove = getMoves(false).isEmpty();
        turn *= -1; //change turn back
        turnNumber--; //change turn number back;
        
        
        boolean gameIsOver = currentPlayerCanNotMove && nextPlayerCanNotMove;
        lastTurnToAskGameIsOver = turnNumber;
        gameIsOverCache = gameIsOver;
        return gameIsOver;
    }
    
    @Override
    public Reversi getCopy() {
        Reversi copy = new Reversi();
        ReversiBoard boardCopy = board.getCopy();
        copy.setBoard(boardCopy);
        return copy;
    }
    
    @Override
    public List<Object> getMoves() {
        return getMoves(true);
    }
    
    /**
     * Returns legal moves in this game situation
     * and puts them in the getMovesCache if that option is chosen.
     * @param cache whether to save result in the cache or not
     * @return legal moves in this game situation
     */
    protected List<Object> getMoves(boolean cache) {
        if (cache) {
            if (lastTurnToAskMoves == turnNumber) {
                return getMovesCache;
            }
        }

        List<Object> availableMoves = new List<>();
        for (int i=0; i<8; i++) {
            for (int j=0; j<8; j++) {
                if (moveIsLegal(board, turn, i, j)) {
                    availableMoves.add(new IntPair(i, j));
                }
            }
        }
        
        if (cache) {
            getMovesCache = availableMoves;
            lastTurnToAskMoves = turnNumber;
        }
        
        return availableMoves;
    }
    
    protected boolean moveIsLegal(ReversiBoard board, int turn, int x, int y) {
        if (board.getBoardXY(x, y) != 0) {
            return false;
        }
        return ReverseHelper.reverse(board, turn, x, y, true);
    }
    
    @Override
    public String toString() {
        String temp = board.toString();
        if (!gameIsOver()) {
            temp += "It's " + getPlayer(getTurn()) + "'s turn.\n";
        } else {
            int score = getScore();
            int points = score < 0 ? score*-1 : score;
            temp += "Winner is " + getPlayer(score) + 
                    " with " + points + " points.\n";
        }
        return temp;
    }
    
    private static String getPlayer(int i) {
        if (i<0) {
            return "black";
        } else if (i>0) {
            return "white";
        } else {
            return "no one";
        }
    }
    
    /**
     * Makes the suggested move and changes turn if it is legal;
     * returns whether the move is legal.
     * @param x row number of the move
     * @param y column number of the move
     * @return true if move is legal, false if not
     */
    public boolean move(int x, int y){
        /**
         * It's not allowed to play on an occupied space.
         */
        if (board.getBoardXY(x, y) != 0) {
            return false;
        }
        
        /**
         * If the move does not reverse any pieces of the opposing color,
         * the move is not legal.
         */

        boolean moveFlips = ReverseHelper.reverse(board, turn, x, y, false);
         
        if (moveFlips) {
            board.setBoardXY(x, y, turn);
            turnNumber++;
            turn *= -1;
            return true;
        } else {
            return false;
        }   
    }
    
    public ReversiBoard getBoardCopy() {
        return board.getCopy();
    }

    @Override
    public boolean move(Object move) {
        IntPair pair = (IntPair) move;
        if (pair == null) {
            turnNumber++;
            turn *= -1;
            return true;
        }
        return move(pair.getX(), pair.getY());
    }

    @Override
    public int winner() {
        if (!gameIsOver()) {
            throw new java.lang.IllegalStateException(
                    "Winner asked but game is not over.");
        }
        return getScore() > 0 ? 1 : -1;
    }
    
 
}
