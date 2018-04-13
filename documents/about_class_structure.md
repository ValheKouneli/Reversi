## 13.4.2018

There is an interface called Game and a class called Reversi which implements Game.

Reversi has-a ReversiBoard. ReversiBoard stores information about the board state. Reveri takes care of moving pieces on the ReversiBoard according to the game rules.

Reversi's method move and moveIsLegal use ReverseHelper.

Package reversi.data_structures includes classes such as List that are used all over the project.

Package reversi.AI contains classes related to minimax-based reversi bot. Bot does not know class Reversi but only the interface Game.

There is a class AI in package revesi.AI. There is also an interface called Evaluator in the same package. AI can be given an Evaluator in its constructor.

Classes ReversiEvaluator1 and ReversiEvaluator2 implement Evaluator.

In package reversi.AI.MCTS there is a class called MCTSbot which uses classes Node, State, Tree and UCT in the same package. MonteCarloTreeSearch is basically another game bot that knows the interface Game.
