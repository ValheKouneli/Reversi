package reversi.AI.MCTS;

import reversi.data_structures.Node;
import reversi.game.reversi.BoardFactory;
import reversi.game.reversi.Reversi;
import reversi.game.reversi.ReversiBoard;

/**
 *
 * @author Valhe Kouneli
 */
public class MCTSTestHelper {
    
    private MCTSTestHelper() {}
    
    public static final void addNewChild(int childWinScore, int childVisitCount, Node parent) {
        MCTSState childState = new MCTSState();
        childState.setVisitCount(childVisitCount);
        childState.setWinScore(childWinScore);
        Node child = new Node(childState);
        child.setParent(parent);
        parent.addChild(child);
        ((MCTSState) parent.getState()).setVisitCount(((MCTSState) parent.getState()).getVisitCount() +
                childVisitCount);
        ((MCTSState) parent.getState()).setWinScore(((MCTSState) parent.getState()).getWinScore() + 
                childWinScore);
    }
    
    public static Node getTestNodeWithChildrenButNoSetGames() {
        Node parent = new Node(new MCTSState());
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
                                                    "0 |○|●|●|●|●|●|●|●|\n" +
                                                    "1 |○|○|○|○|○|○|○|●|\n" +
                                                    "2 |○|●|●|●|○|○|●|●|\n" +
                                                    "3 |○|●|○|●|○|○|○|●|\n" +
                                                    "4 |○|●|●|●|●|○|○|●|\n" +
                                                    "5 |○|●|●|○|○|○|○|●|\n" +
                                                    "6 |○| |○|○|○|○|○|●|\n" +
                                                    "7 | |○|○|○|○|○|○|●|\n");
        game.setBoard(board);
        MCTSState state = new MCTSState(game);
        Node node = new Node(state);
        return node;
    }
    
    public static Node getTestNodeWithChildrenWithInfenitelyBadScores() {
        Node node = getTestNodeWithSetGameButNoChildren();
        MCTShelper mctshelper = new MCTShelper();
        mctshelper.expandNode(node);
        Node child;
        for(int i=0; i<node.getChildren().size(); i++) {
            child = node.getChildren().get(i);
            ((MCTSState) child.getState()).setWinScore(Integer.MIN_VALUE);
        }
        return node;
    }
}
