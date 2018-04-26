package reversi.game;

/**
 *
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
    private Player winner;
    
    public Match(Game game, Player player1, Player player2) {
        this.game = game;
        timeSpentTotal = 0;
        winner = null;
        this.player1 = player1;
        this.player2 = player2;
        timeSpentPlayer1 = 0;
        timeSpentPlayer2 = 0;
        movesPlayedPlayer1 = 0;
        movesPlayedPlayer2 = 0;
    }
    
    public void playMatch() {
        playMatch(false);
    }
    
    public void playMatch(boolean print) {
        long timeBeforeMatch = System.currentTimeMillis();
        long timeBeforeMove;
        long moveTime;
        long timeAfterMove;
        if (print) {
            System.out.println(game.toString());
        }
        while (!game.gameIsOver()) {

            System.out.println(game.toString());
            int turn = game.getTurn();
            Player playerInTurn = turn == 1 ? player1 : player2;
            
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
            if (print) {
                System.out.println(game.toString());
            }
        }
        long timeAfterMatch = System.currentTimeMillis();
        
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
        if (movesPlayedPlayer1 == 0) {
            return 0;
        } else {
            return timeSpentPlayer1 / movesPlayedPlayer1;
        }
    }
    
    public long averageTimePerMovePlayer2() {
        if (movesPlayedPlayer2 == 0) {
            return 0;
        } else {
            return timeSpentPlayer2 / movesPlayedPlayer2;
        }
    }
    
    public int returnWinner() {
        return game.winner();
    }
    
    public String returnWinnerName() {
        return winner.name();
    }
    
    public long averageTimePerMove() {
        if (movesPlayedPlayer1 + movesPlayedPlayer2 == 0) {
            return 0;
        } else {
            return (timeSpentPlayer1 + timeSpentPlayer2) /
                    (movesPlayedPlayer1 + movesPlayedPlayer2);
        }
    }

    
}
