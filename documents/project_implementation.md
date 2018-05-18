Implementation
==============

For given depths, first, my program creates a minimax bot and makes it play against itself. It records the time it takes per move on average. For this, my program uses the class RecordMoveTimeMinimax.

After that, the program creates a mcts bot and gives it the same amount of time the minimax took on avarage. Then, the program makes the bots play against each other and keeps statistics of the results. For this, the program uses the class ScoreKeeper. ScoreKeeper, in turn, uses the class Match.

After each depth, the program asks the results from the ScoreKeeper and writes them in a new file and appends them in an existing file that contains old results too.

After doing all this for all the depths, the program reads the resuls from the new file and prints them. After that, it creates a line chart about the win percentages of mcts bot agaist minimax AI for different depths. The line chart data is read from the file that contains the old results too.

See the class diagrams and javadoc for more detailed information about the implementation.
