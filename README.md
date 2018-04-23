![travis](https://travis-ci.org/ValheKouneli/Reversi.svg?branch=master)
[![Code Coverage](https://img.shields.io/codecov/c/github/ValheKouneli/Reversi/master.svg)](https://codecov.io/github/ValheKouneli/Reversi/)

Reversi
=======

Command line Java application, Reversi AI.

## Bugs (23.4.2018)

* My interpretation of the game rules might differ from the official rules. If either player can not play, the game ends.
* CLIHumanPlayer is buggy. At some point, the program stops accepting valid moves, claiming they are illegal.

## TODO (at least)

* Fix CLIHumanPlayer (see bugs)
* Remove the hidious <MoveType> <GameType> thingies from the AIs :O~
* Fix the implementation of rules (see bugs)
* Make more unit tests
