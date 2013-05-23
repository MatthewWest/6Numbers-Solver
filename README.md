# 6Numbers Solver
This small command-line application solves problems of the form seen in the iOS game 6Numbers. The problem consists of a goal number and a collection of other numbers (6Numbers uses 6 numbers, hence its name). The goal is to add, subtract, multiply, and divide the “other numbers” in any order until you reach the goal number, using all or a subset of them.

## Installation
If you have Java, there is no need to install anything. Just download and unzip the ZIP archive. Or even better, fork the repository and improve it!

## Usage
There are two primary ways you can use this program: as a Java command-line tool, or as a JavaScript webpage.

### Command Line
For the command-line tool, if you have Clojure and Leiningen installed and want to run the project from source, navigate to the unzipped 6Numbers-Solver folder in the command line and enter 
`lein run`.

If you’d rather not install Clojure, navigate to the 6Numbers-Solver/target folder, then run:

    $ java -jar six-numbers-solver-0.1.0-standalone.jar
    
For either command line method, just follow the prompts to enter the goal number and collection of numbers. The program will solve them (though not always in the most efficient way due to its design), and print its solution, then ask if you want to solve another problem.

### Webpage
You can try the webpage [here](http://matthewwest.github.io/6Numbers-Solver/resources/public/solver.html), then when you want to use the page on your own computer, just navigate to the `6Numbers-Solver/resources/public/` folder and open the file entitled `solver.html`. That’s it! Just enter your numbers into the web form and press **Solve**. Be sure to have JavaScript enabled, or the webpage won’t do anything.

## Options
There is only one option, script mode, and it is only usable in the Command line mode.

###Usage
To use script mode, run the program as before (`java -jar six-numbers-solver-0.1.0-standalone.jar`), but append `-c [target [numbers to use]]` on the end. This will return a string containing the solution for use in other programs, with newlines escaped by `\n` .

The eventual hope is to make this mode the backend for a system-native 6numbers clone.

### Bugs

No known bugs strictly speaking, just lacks of features.

## License

Copyright © 2013 Matthew A. West

Distributed under the Eclipse Public License, the same as Clojure.
