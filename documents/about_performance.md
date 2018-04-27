About performance
=================

Both MinimaxAI (depth >3) and MCTS Bot (>500 ms/move) beat me easily in a game of Reversi. I'm not an experienced player.

MCTS Bot seems to win MinimaxAI when MinimaxAI's search depth is 5 and MCTS Bot is given the avarage amount of time MinimaxAI takes to decide a move.

MinimaxAI's time complexity is O(n²) relative to the search depth and MCTSBot's getNextMove method's time complexity is contant, because it is given a time limit.
