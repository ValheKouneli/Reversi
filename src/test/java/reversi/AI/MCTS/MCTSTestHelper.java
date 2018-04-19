/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reversi.AI.MCTS;

import reversi.AI.Game;
import reversi.game.BoardFactory;
import reversi.game.Reversi;
import reversi.game.ReversiBoard;

/**
 *
 * @author Valhe Kouneli
 */
public class MCTSTestHelper {
    
    private MCTSTestHelper() {}
    
    public static final void addNewChild(int childWinScore, int childVisitCount, Node parent) {
        State childState = new State();
        childState.setVisitCount(childVisitCount);
        childState.setWinScore(childWinScore);
        Node child = new Node(childState);
        child.setParent(parent);
        parent.addChild(child);
        parent.getState().setVisitCount(parent.getState().getVisitCount() +
                childVisitCount);
        parent.getState().setWinScore(parent.getState().getWinScore() + 
                childWinScore);
    }
    
    public static Node getTestNodeWithChildrenButNoSetGames() {
        Node parent = new Node(new State());
        MCTSTestHelper.addNewChild(2, 2, parent); //3.17377697326
        MCTSTestHelper.addNewChild(4, 4, parent); //2.53709243858
        MCTSTestHelper.addNewChild(1, 3, parent); //2.1082147997
        MCTSTestHelper.addNewChild(5, 6, parent); //2.088364054
        MCTSTestHelper.addNewChild(100, 101, parent); //1.29599183814
        return parent;
    }
    
    public static Node getTestNodeWithSetGameButNoChildren() {
        Reversi game = new Reversi();
        ReversiBoard board = BoardFactory.makeBoard("   0 1 2 3 4 5 6 7 \n" +
                                                    "0 |●|○|○|○|○|○|○|○|\n" +
                                                    "1 |●|●|●|●|●|●|●|○|\n" +
                                                    "2 |●|○|○|○|●|●|○|○|\n" +
                                                    "3 |●|○|●|○|●|●|●|○|\n" +
                                                    "4 |●|○|○|○|○|●|●|○|\n" +
                                                    "5 |●|○|○|●|●|●|●|○|\n" +
                                                    "6 |●| |●|●|●|●|●|○|\n" +
                                                    "7 | |●|●|●|●|●|●|○|\n");
        game.setBoard(board);
        game.setTurn(1);
        game.setTurnNumber(62);
        State state = new State();
        Node node = new Node(state);
        return node;
    }
}
