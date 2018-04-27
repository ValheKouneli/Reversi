Progress Report: Week 6
=======================

## Overview

I started the week by trying to make an UI, but then gave up, because I set my standards too high and couldn't do what I wanted in the end.

In the end, I ended up making a simple program that tests the two types of bots against each other.

**Total time spent: 25 h**

## What I learned

I learned to prioritize tasks. I realized that when following Scrum for example, there is a point in setting a priority to each story. I also learned some useful things about testing for exceptions and testing for output.
I found it hard to keep myself from making more and more additional features to my program before the previous ones had been properly tested and documented.

## Weekly reports

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

Friday: I created classes to help make statistics of the two bots and to match them against each other in a fair way. I made a lot of tests too.

Time spent: 6h
