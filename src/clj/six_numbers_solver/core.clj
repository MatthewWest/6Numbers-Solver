(ns six-numbers-solver.core
  (:require [six-numbers-solver.cli :as cli]
            [six-numbers-solver.cmd :as cmd])
  (:gen-class))
(require '[six-numbers-solver.cli :as cli])
(require '[six-numbers-solver.cmd :as cmd])

(defn -main
  "Handle the main program loop"
  [& args]
  ;; work around dangerous default behaviour in Clojure
  (alter-var-root #'*read-eval* (constantly false))

  (if args
    (if (= "-c" (str (first args)))
      (cmd/main (str (second args)))
      nil)
    (loop []
      (if (not (= \y (cli/main)))
        nil
        (recur)))))

(with-out-str (-main '-c "[8 [1 2 3]]"))