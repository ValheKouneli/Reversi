Implementation
==============

For given depths, first, my program creates a minimax bot and makes it play against itself. It records the time it takes per move on average. For this, my program uses the class RecordMoveTimeMinimax.

After that, the program creates a mcts bot and gives it the same amount of time the minimax took on avarage. Then, the program makes the bots play against each other and keeps statistics of the results. For this, the program uses the class ScoreKeeper. ScoreKeeper, in turn, uses the class Match.

After each depth, the program asks the results from the ScoreKeeper and writes them in a new file and appends them in an existing file that contains old results too.

After doing all this for all the depths, the program reads the resuls from the new file and prints them. After that, it creates a line chart about the win percentages of mcts bot agaist minimax AI for different depths. The line chart data is read from the file that contains the old results too.

Download the build to see the class diagrams and javadoc for more detailed information about the implementation.


## Sources

* [http://www.flyordie.com/games/help/reversi/en/games_rules_reversi.html](http://www.flyordie.com/games/help/reversi/en/games_rules_reversi.html)
* [http://www.baeldung.com/java-monte-carlo-tree-search](http://www.baeldung.com/java-monte-carlo-tree-search) ERRONOUS!
* [https://en.wikipedia.org/wiki/Computer_Othello](https://en.wikipedia.org/wiki/Computer_Othello)
* [https://en.wikipedia.org/wiki/Monte_Carlo_tree_search](https://en.wikipedia.org/wiki/Monte_Carlo_tree_search)
* [https://jeffbradberry.com/posts/2015/09/intro-to-monte-carlo-tree-search/](https://jeffbradberry.com/posts/2015/09/intro-to-monte-carlo-tree-search/)
[https://www.cs.swarthmore.edu/~bryce/cs63/s16/slides/2-15_MCTS.pdf](https://www.cs.swarthmore.edu/~bryce/cs63/s16/slides/2-15_MCTS.pdf)
