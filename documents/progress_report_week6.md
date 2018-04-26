Progress Report: Week 6
=======================

Monday: Made a working version of the command line user interface.

Time spent: 3h

Tuesday: Made improvements(?) to user interface classes. Made a time limited Minimax bot (breadth first branching out).

Time spent: 5h

Tuesday: Made Reversi use the official rules for the game, i.e. the game end only when both players can not play.
Made some tests for older classes to make sure they are not the cause for the human player input bug. They weren't. Started to change the user interface – I want to be able to pause the game between to AI's by pressing space. Looked into multi-threading :SS

Time spent: 3h

Thursday: I deleded all UI related because trying to build it was going to any sensible direction. I decided to focus on performance tests. I created a Match class to measure how long the bot's take to move. I made the bots play against each other, giving the MCTSbot the same amount of time the minimaxAI takes to decide a move on average.
The bots play several matches against each other and Main collects statistics.

Time spent: 4h

Thursday: Made a ton of unit tests.

Time spent: 4h
