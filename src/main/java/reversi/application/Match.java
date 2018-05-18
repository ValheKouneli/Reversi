package reversi.application;

import reversi.game.Game;
import reversi.game.Player;
import reversi.game.reversi.Reversi;

/**
 * Represents a single match between two players.
 * @author Valhe Kouneli
 */
public class Match {
    
    private final Player player1;
    private final Player player2;
    private final Game game;
    private int timeSpentPlayer1;
    private int movesPlayedPlayer1;
    private int movesPlayedPlayer2;
    private int timeSpentPlayer2;
    private long timeSpentTotal;
    private boolean matchFinished;
    private Player winner;
    private final String matchName;
    
    public Match(Game game, Player player1, Player player2) {
        this.matchName = "Match no 1";
        this.game = game;
        timeSpentTotal = 0;
        winner = null;
        this.player1 = player1;
        this.player2 = player2;
        timeSpentPlayer1 = 0;
        timeSpentPlayer2 = 0;
        movesPlayedPlayer1 = 0;
        movesPlayedPlayer2 = 0;
        matchFinished = false;
    }
    
    public Match(String matchName, Game game, Player player1, Player player2) {
        this.matchName = matchName;
        this.game = game;
        timeSpentTotal = 0;
        winner = null;
        this.player1 = player1;
        this.player2 = player2;
        timeSpentPlayer1 = 0;
        timeSpentPlayer2 = 0;
        movesPlayedPlayer1 = 0;
        movesPlayedPlayer2 = 0;
        matchFinished = false;
    }
    
    /**
     * Plays a match between the two players.
     */
    public void playMatch() {
        playMatch(false);
    }
    
    /**
     * Plays a match between the two players.
     * @param print the match or not
     */
    public void playMatch(boolean print) {
        playMatch(print, false);
    }
    
    /**
     * Plays a match between the two players.
     * @param print the match or not
     * @param printProgress of the match or not
     */
    public void playMatch(boolean print, boolean printProgress) {
         long timeBeforeMatch = System.currentTimeMillis();
        long timeBeforeMove;
        long moveTime;
        long timeAfterMove;
        if (print) {
            System.out.println("====================================\n");
            System.out.println("STARTING: " + matchName);
            System.out.println("Black player: " + player2.name());
            System.out.println("White player: " + player1.name());
            System.out.println("\n");
            System.out.println("____________________________");
        }
        while (!game.gameIsOver()) {
            if (print) {
                System.out.println("PLAYING: " + matchName);
                System.out.println();
                System.out.println(game.toString());
                System.out.println("____________________________");
            }
            int turn = game.getTurn();
            Player playerInTurn = turn == 1 ? player1 : player2;
            
            if (printProgress) {
                System.out.println("Turn " + (game.getTurnNumber()+1) +
                        " in progress.");
            }
            timeBeforeMove = System.currentTimeMillis();
            Object move = playerInTurn.getNextMove(game);
            timeAfterMove = System.currentTimeMillis();
            
            moveTime = timeAfterMove - timeBeforeMove;
            if (turn == 1) {
                timeSpentPlayer1 += moveTime;
                movesPlayedPlayer1++;
            } else {
                timeSpentPlayer2 += moveTime;
                movesPlayedPlayer2++;
            }
            
            game.move(move);
        }
        long timeAfterMatch = System.currentTimeMillis();
        matchFinished = true;
        
        if (print) {
            System.out.println(game.toString());
            System.out.println(player1.name() + " used " + 
                    averageTimePerMovePlayer1() +
                    " ms per move on average.");
            System.out.println(player2.name() + " used " + 
                    averageTimePerMovePlayer2() +
                    " ms per move on average.");
        }

        timeSpentTotal = timeAfterMatch - timeBeforeMatch;
        
        switch (game.winner()) {
            case 1:
                winner = player1;
                break;
            case -1:
                winner = player2;
                break;
            default:
                winner = null;
                break;
        }
    }
    
    public long averageTimePerMovePlayer1() {
        if (!matchFinished) {
            throw new java.lang.IllegalStateException("Game is not over yet.");
        }
        if (movesPlayedPlayer1 == 0) {
            return 0;
        } else {
            return timeSpentPlayer1 / movesPlayedPlayer1;
        }
    }
    
    public long averageTimePerMovePlayer2() {
        if (!matchFinished) {
            throw new java.lang.IllegalStateException("Game is not over yet.");
        }
        if (movesPlayedPlayer2 == 0) {
            return 0;
        } else {
            return timeSpentPlayer2 / movesPlayedPlayer2;
        }
    }
    
    public int returnWinner() {
        if (!matchFinished) {
            throw new java.lang.IllegalStateException("Game is not over yet.");
        }
        return game.winner();
    }
    
    public String returnWinnerName() {
        if (!matchFinished) {
            throw new java.lang.IllegalStateException("Game is not over yet.");
        }
        if (winner == null) {
            return "Tie";
        }
        return winner.name();
    }
    
    public long averageTimePerMove() {
        if (!matchFinished) {
            throw new java.lang.IllegalStateException("Game is not over yet.");
        }
        if (movesPlayedPlayer1 + movesPlayedPlayer2 == 0) {
            return 0;
        } else {
            return (timeSpentPlayer1 + timeSpentPlayer2) /
                    (movesPlayedPlayer1 + movesPlayedPlayer2);
        }
    }
    
    public long timeSpentTotal() {
        if (!matchFinished) {
            throw new java.lang.IllegalStateException("Game is not over yet.");
        }
        return timeSpentTotal;
    }
    
    @Override
    public String toString() {        
        if (!matchFinished) {
            throw new java.lang.IllegalStateException("Game is not over yet.");
        }
        String temp = "Match between \n" +
                        player2.name() + " (black) &\n" +
                        player1.name() + " (white)\n" +
                        "ended in " + returnWinnerName() + " victory\n";
        try {
            Reversi reversi = (Reversi) game;
            temp += "by " + Math.abs(reversi.getScore()) + " points.";
        } catch (ClassCastException e) {
            //do nothing
        }
        return temp;
    }

    
}
