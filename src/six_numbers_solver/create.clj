(ns six-numbers-solver.create
  (:gen-class))

(defn random-operator
  "Select a random operator, * + / or -"
  []
  (case (rand-int 4)
         0 *
         1 +
         2 /
         3 -
         :else
         #(str "Your arguments were " %1 " and " %2 "... and something went wrong.")))

(defn is-valid?
  "Return x if it is a positive integer not greater than 1000, otherwise nil"
  [x]
  (if (and
       (pos? x)
       (= java.lang.Long (type x))
       (> 1000 x))
    x
    nil))

(defn add-number
  "Return a list of the form [newgoal number-used-to-get-there]"
  [old-goal]
  (let [new-trial (+ 1 (rand-int 100))]
    ))

(defn create-new-problem
 "Create a new problem with # of steps, then add # of decoys. Return a list of the form [target [collection]]"
 ([steps
  decoys]

 )
 ([steps]
  (create-new-problem steps 0)))

