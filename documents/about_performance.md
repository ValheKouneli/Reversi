About performance
=================

Both MinimaxAI (depth >3) and MCTS Bot (>500 ms/move) beat me easily in a game of Reversi. I'm not an experienced player.

MinimaxAI's getNextMove method's time is O(b^n) where b is the number of legal moves and n is the search depth. I did not have time to collect data about the average number of legal moves per game. MCTSBot's getNextMove method's time complexity is contant, because it is given a time limit.
