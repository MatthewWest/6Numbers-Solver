(ns six-numbers-solver.core
  (:gen-class))
(require '[six-numbers-solver.cli :as cli])

(defn -main
  "Handle the main program loop"
  [& args]
  ;; work around dangerous default behaviour in Clojure
  (alter-var-root #'*read-eval* (constantly false))
  (loop []
    (if (not (= \y (cli/main)))
      nil
      (recur))))
