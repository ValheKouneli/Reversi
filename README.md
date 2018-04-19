![travis](https://travis-ci.org/ValheKouneli/Reversi.svg?branch=master)
[![Code Coverage](https://img.shields.io/codecov/c/github/ValheKouneli/Reversi/master.svg)](https://codecov.io/github/ValheKouneli/Reversi/)

Reversi
=======

Command line Java application, Reversi AI.

## Current known bugs (11.4.2018)

* MCTSbot crushes because of null pointer exception in some situations.
* My interpretation of the game rules might differ from the official rules. If either player can not play, the game ends.

## TODO (at least)

* Make a test for a situation that crashed the MCTSbot. Fix the bug. (see current known bugs)
* Fix the implementation of rules (see current known bugs)
* Make command line user interface
* Make more unit tests
