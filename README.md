![travis](https://travis-ci.org/ValheKouneli/Reversi.svg?branch=master)
[![Code Coverage](https://img.shields.io/codecov/c/github/ValheKouneli/Reversi/master.svg)](https://codecov.io/github/ValheKouneli/Reversi/)

Reversi
=======

Command line Java application, Reversi AI.

Program compares the Minimax algorithm and the Monte Carlo Tree Search (MCTS) algorithm against each other when using them to play the game of Reversi (Othello).

For given range of search depths, program first measures how long the Minimax algorithm takes to decide a move. Then, it sets that time as a time limit to the MCTS algorithm.
After that, the program makes bots using these algorithms play given number of Reversi matches against each other, alternating the player colors, and prints statistics of wins and losses and used time.

After that, the program shows a chart showing how often MCTS Bot wins against Minimax AI for given depths. For this, the program uses data collected over several runs.

You can give the search depth limits as an arguments to the program. You can also give the number of game simulations per depths as a third argument. Giving only one argument sets the upper limit only.

***
<span style="background-color: #FFFF00">Note that these instruction assume you are using the Linux Operating System!</span>
***

### Instructions On How To Use

To simply run the project, download the Reversi.jar file found under ```/build/libs``` and ```raw_data_legacy_results.txt``` into the same repository. In the repository where they are located, run command

```java -cp Reversi.jar reversi.application.Main```

You can give number parameters to the program, either:
* maxDepth
* minDepth maxDepth
* minDepth maxDepth matchesPerDepth

The default values are minDepth=3, maxDepth=5 and matchesPerDepth=10

Notice that by using the default values, the program will take quite a while to run (~ 15 mins).

For the shortest run time, use

```java -cp Reversi.jar reversi.application.Main 2 2 2```

### Altering the program

The program can be easily altered (by an adequately skilled programmer) to do other things besides comparing the Minimax and MCTS algorithms in the game of Othello.
The project includes Player, Game, and Evaluator interfaces.
* Using the Evaluator interface, excisting Minimax bot can be made to use a different evaluation method for the game situation.
* Using the Game interface, excisting bots can be made to play other games besides Othello. New Evalutor must be made for Minimax Bot, as Evaluators are Game-dependent.
* Using the Player interface, new bots can be made, or a human user can play against excisting bots.

Different search depths can be tested. Deeper searches results in a longer run time, of course.

### Developing

The project uses Gradle. Assuming you have Gradle installed, after cloning the project and possibly making changes, in the project depository, run

```gradle build```

```gradle jar```

and an updated Reversi.jar file will appear in ```/build/lib/```.
The file can be run by in the root repository by typing

```java -cp ./build/lib/Reversi.jar reversi.application.Main```

For more help, see the files under ```/documentation``` and ```/build/docs```

### Bugs and Critique

Reversi's method getMoves caches the moves into getMovesCache and returns it. It does not cache a copy of the returned list. The problem is that if the returned list is modified, the cached list changes as well. The program, in its current state however, does not modify the list it gets from Reversi.

This could be fixed easily if List had a clone or getCopy method. Implementation is not easy, however, because the list would have to clone its contents; I have named my other cloning methods as getCopy and not clonable.

Everything in package ```application``` is terrible, because I had so little time to implement them.
