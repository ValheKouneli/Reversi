About tests
===========

Unit test are made with JUnit. They don't cover package reversi.application, because it not part of essential application logic and because I did not have enough time.

The test will take relatively long time to run (~ 1 min).

Run tests with gradle test and (assuming the build is succesful) and check the coverage report in /build/reports/jacoco/test/html/index.html.

Running Main.java tests the performance of the MinimaxAI Reversi bot and MCTS Reversi Bot against each other and draws a graph of the results.
