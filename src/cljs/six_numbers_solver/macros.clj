(ns six-numbers-solver.macros)

(defmacro reify-bool
  [statement]
  `(if ~statement 1 0))

