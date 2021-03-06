(ns six-numbers-solver.cli
  (:gen-class))
(require 'six-numbers-solver.calc)

(defn ask-for-input
  "Show the given prompt and return the input"
  [prompt]
  (println prompt)
  (read-line))

(defn get-all-inputs
  "Prompt for inputs and return formatted goal and collection"
  []
  (loop [goal nil
         collection []]
    (if (nil? goal)
      (recur (ask-for-input "Enter the target number: ") [])
      (let [next-entry
            (ask-for-input
             "Enter a number in the collection, or leave blank to leave entry mode: ")]
        (if (not (= "" next-entry))
          (recur goal (cons (read-string next-entry) collection))
          [(read-string goal) collection])))))

(defn main
  "Runs one interaction with the user, then returns with whether or not to solve another problem"
  []
  (let [[goal
         collection]
        (get-all-inputs)]
    (println
     (str "Target: " goal "\nNumbers to use: " collection))
    (println "Solving...")
    (println (six-numbers-solver.calc/solve goal collection)))
  (if (= \n
         (first
          (ask-for-input "Solve another problem? (y/n) ")))
    nil
    (recur)))
