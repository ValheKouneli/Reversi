/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reversi.AI.MCTS;

/**
 *
 * @author Valhe Kouneli
 */
public class Tree {
    
    Node root;
    
    public Tree() {
        root = new Node();
    }
    
    public Node getRoot() {
        return root;
    }
    
}
