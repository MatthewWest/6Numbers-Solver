(ns six-numbers-solver.calc
  (:require [clojure.math.combinatorics
             :as comb]))

(defn rtlevaluate ;; TODO make resistant to divide-by-0
    "Recursively evaluates left-to-right a list that alternates between operators and values"
    ([x] x)
    ([x op & more]
     (op (apply rtlevaluate more) x)))

(defn evaluate
  "Evaluates left-to-right a list alternating between operators and values"
  [coll]
  (apply rtlevaluate (reverse coll)))

(defn is-solution?
  "Checks to see whether a given combination of operators and numbers is a solution"
  [guess ans]
  (= ans (evaluate guess)))

(defn guesses [inputs]
  "Generates all possible guesses of interspersing inputs with operators"
  (let [operators [+ - * /]]
    (for [order (comb/subsets inputs)
          oporder (comb/selections operators (dec (count order)))]
      (concat (interleave order oporder) [(last order)]))))

(defn pprint [coll]
  (if (nil? coll)
    nil
    (->>
     (concat (into [] coll) [\= (evaluate coll)])
     (interpose " ")
     (replace {* \* + \+ / \/ - \-})
     (reduce str))))

(defn solve
  [ans inputs]
  (pprint (some (fn [coll] (if-let [sol (is-solution? coll ans)]
                             coll))
                (guesses inputs))))
