About tests
===========

### Unit tests

Unit test are made with JUnit. They don't cover package reversi.application, because it not part of essential application logic and because I did not have enough time.

You can see the test resulsts and coverage report in ```/build/reports```.

(Download the build to view the reports on your browser.)

The test will take relatively long time to run (~ 1 min).

Run tests with gradle test and (assuming the build is succesful) and check the coverage report in /build/reports/jacoco/test/html/index.html.

### Performance tests

Running Main.java tests the performance of the MinimaxAI Reversi bot and MCTS Reversi Bot against each other. It prints how much time Minimax AI took deciding a move for given search depth on average. In the end, the program draws a graph of how often the MCTS Bot beat the Minimax AI per search depth.
