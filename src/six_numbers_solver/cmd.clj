(ns six-numbers-solver.cmd
  (:gen-class))
(require '[six-numbers-solver.calc :as calc])
(require '[clojure.string :as string])

(defn parse-arguments
  "Parse a string of arguments to goal and collection"
  [argument-string]
  (let [[goal
         collection]
        (read-string argument-string)]
    (if (and
         (instance? java.lang.Long goal)
         (coll? collection))
      [goal collection]
      nil)))

(defn answer-out
  "Stdout the solution, returning false if it wasn't found."
  [solution]
  (if solution
    (print solution)
    (print false)))

(defn main
  "Main dispatch for the script interface"
  [arguments]
  (let [args (parse-arguments arguments)]
    (if args
      (let [[goal collection] args]
        (answer-out (calc/solve goal collection)))
      (answer-out nil))))
