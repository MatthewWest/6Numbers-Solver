# 6Numbers Solver

This small command-line application solves problems of the form seen in the iOS game 6Numbers. The problem consists of a goal number and a collection of other numbers (6Numbers uses 6 numbers, hence its name). The goal is to add, subtract, multiply, and divide the “other numbers” in any order until you reach the goal number, using all or a subset of them.

## Installation

If you have Java, there is no need to install anything. Just download and unzip the ZIP archive. Or even better, fork the repository and improve it!

## Usage

If you have Clojure and Leiningen installed and want to run the project from source, navigate to the unzipped 6Numbers-Solver folder in the command line and enter 
`lein run`.

If you’d rather not install Clojure to run the project, navigate to the 6Numbers-Solver/target folder, then run:

    $ java -jar six-numbers-solver-0.1.0-standalone.jar
    
Then follow the prompts to enter the goal number and collection of numbers. The program will solve them (#FIXME: not always in the most efficient way), and print its solution, then ask if you want to solve another problem.

## Options

No options yet... some may be coming in the future.

### Bugs

No known bugs strictly speaking, just lacks of features.

## License

Copyright © 2013 Matthew A. West

Distributed under the Eclipse Public License, the same as Clojure.
