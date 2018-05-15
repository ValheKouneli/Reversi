![travis](https://travis-ci.org/ValheKouneli/Reversi.svg?branch=master)
[![Code Coverage](https://img.shields.io/codecov/c/github/ValheKouneli/Reversi/master.svg)](https://codecov.io/github/ValheKouneli/Reversi/)

Reversi
=======

Command line Java application, Reversi AI.

Program compares the Minimax algorithm and the Monte Carlo Tree Search (MCTS) algorithm against each other when using them to play the game of Reversi (Othello).

For three different search depths, program first measures how long the Minimax algorithm takes to decide a move. Then, it sets that time as a time limit to the MCTS algorithm.
After that, the program makes bots using these algorithms play ten Reversi matches against each other, alternating the player colors, and shows statistics of wins and losses and used time.

***
<span style="background-color: #FFFF00">Note that these instruction assume you are using the Linux Operating System!</span>
***

### Instructions On How To Use

To simply run the project, download the Reversi.jar file. In the repository where the .jar file is located, run command
```java -cp Reversi.jar reversi.application.Main```

### Altering the program

The program can be easily altered (by an adequately skilled programmer) to do other things besides comparing the Minimax and MCTS algorithms in the game of Othello.
The project includes Player, Game, and Evaluator interfaces.
    *Using the Evaluator interface, excisting Minimax bot can be made to use a different evaluation method for the game situation.
    *Using the Game interface, excisting bots can be made to play other games besides Othello. New Evalutor must be made for Minimax Bot, as Evaluators are Game-dependent.
    *Using the Player interface, new bots can be made, or a human user can play against excisting bots.
Different search depths can be tested. Deeper searches results in a longer run time, of course.

### Developing

The project uses Gradle. Assuming you have Gradle installed, after cloning the project and possibly making changes, in the project depository, run
```gradle build```
```gradle jar```
and an updated Reversi.jar file will appear in ```/build/lib/```.
The file can be run by in the root repository by typing
```java -cp ./build/lib/Reversi.jar reversi.application.Main```

For more help, see the files under /documentation.
