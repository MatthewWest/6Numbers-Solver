(ns six-numbers-solver.core
  (:gen-class))
(require '[six-numbers-solver.ui :as ui])

(defn -main
  "Handle the main program loop"
  [& args]
  ;; work around dangerous default behaviour in Clojure
  (alter-var-root #'*read-eval* (constantly false))
  (loop []
    (if (not (= \y (ui/main)))
      nil
      (recur))))
