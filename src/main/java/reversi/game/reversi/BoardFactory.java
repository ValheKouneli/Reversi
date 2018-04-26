package reversi.game.reversi;

/**
 *
 * @author aepiiroi
 */
public class BoardFactory {
    
    private BoardFactory() {}
    
    public static ReversiBoard makeBoard(String boardRepresentation) {
        
        ReversiBoard board = new ReversiBoard();
        String boardString = board.toString();
        int k = 20;
        int c;

        for (int i=0; i<8; i++) {
            k += 3;
            for (int j=0; j<8; j++) {
               c = boardRepresentation.charAt(k);
                switch (c) {
                    case ' ':
                        board.setBoardXY(i, j, 0);
                        break;
                    case '\u25CF':
                        board.setBoardXY(i, j, -1);
                        break;
                    case '\u25CB':
                        board.setBoardXY(i, j, 1);
                        break;
                    default:
                        //throw something
                        break;
                }
                boardString = board.toString();
                k += 2; 
            }
            k += 1;
        }
        return board;
    }
    
}
