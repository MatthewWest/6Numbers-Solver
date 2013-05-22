(ns six-numbers-solver.calc)


(defn is-valid? [goal
                 item]
  (and
   (not (nil? item))
   (pos? item)
   (= item (int item))
   (>=
      (* 10 goal)
      item)))



(defn in-list? [goal
                collection]
  (some #(= % goal) collection))


(defn divide-by-each [goal
                      collection]
  (map
   #(if (is-valid? goal %)
      %
      nil)
   (map
    #(/ goal %)
    collection)))


(defn add-to-each [goal
                   collection]
  (map
   #(if (is-valid? goal %)
      %
      nil)
   (map
    #(+ goal %)
    collection)))


(defn subtract-by-each [goal
                        collection]
  (map
   #(if (is-valid? goal %)
      %
      nil)
   (map
    #(- goal %)
    collection)))


(defn multiply-by-each [goal
                        collection]
  (map
   #(if (is-valid? goal %)
      %
      nil)
   (map
    #(* goal %)
    collection)))


(defn solved-by? [operate-on-by-each ;; a fn which gives a list of one number with each other number applied to it
                  goal
                  collection]
  (some
   #(if (nil? %)
      nil
      %)
   (let [original collection
         operated (operate-on-by-each goal original)]
     (for [i (range (count original))
           v (range (count original))
           :when (not (= i v))]
       (if (= (nth original i)
              (nth operated v))
         [(nth original i)
          (nth original v)]
         nil)))))


(defn remove-index [i
                    collection]
  (concat
   (take i collection)
   (drop (+ 1 i) collection)))


(defn list-for-recursion [goal
                          original-coll
                          operate-on-each
                          op-symbol]
  (filter
    #(is-valid? goal (second %))
    (let [operated-on (operate-on-each goal original-coll)]

      ;; A list of ["Operation needed to get to it" num-from-divide-each new-collection] for each element of divide-each
      (for [i (range (count operated-on))]
        [(str (nth operated-on i)
           op-symbol
           (nth original-coll i)
           " = "
           goal)
         (nth operated-on i)
         (remove-index i original-coll)]))))


(defn solve-n-deep [goal
                    collection
                    n]


  ;; First test if the solution is found at depth 1
  (let [one-deep-attempt (if (in-list? goal collection) ;; Code for a one-deep search
                           (str goal " = " goal)
                           (let [mul-solution (solved-by? divide-by-each goal collection)
                                 sub-solution (solved-by? add-to-each goal collection)
                                 div-solution (solved-by? multiply-by-each goal collection)
                                 add-solution (solved-by? subtract-by-each goal collection)]
                             (cond
                              mul-solution
                              (str (first mul-solution) " * " (second mul-solution) " = " goal)
                              sub-solution
                              (str (first sub-solution) " - " (second sub-solution) " = " goal)
                              div-solution
                              (str (first div-solution) " / " (second div-solution) " = " goal)
                              add-solution
                              (str (first add-solution) " + " (second add-solution) " = " goal)
                              :else
                              nil)))]


    (if (= 1 n) ;; If we're only asked for a one-deep solution...
      one-deep-attempt ;; Return the results of a one-deep search
      (if one-deep-attempt ;; If we're asked for greater than a one-deep search, first test if the solution is one-deep
        one-deep-attempt ;; If we find a solution, return it
        (let [divide-solutions (some ;; Return for all the forwards multiplication (division back from the goal) options
                                ;; A function which tries to solve n-1 levels deep for the second (num) of its argument
                                #(let [trial (solve-n-deep (second %)
                                                           (nth % 2)
                                                           (- n 1))]
                                   (if trial
                                     (str trial "\n" (first %))
                                     nil))
                                ;; Returns a list of the form ["formula to get from newgoal to goal" newgoal [collection left]]
                                (list-for-recursion goal collection divide-by-each " * "))]
          (if divide-solutions
            divide-solutions
            (let [add-solutions (some
                                 #(let [trial (solve-n-deep (second %)
                                                            (nth % 2)
                                                            (- n 1))]
                                    (if trial
                                      (str trial "\n" (first %))
                                      nil))
                                 (list-for-recursion goal collection add-to-each " - "))]
              (if add-solutions
                add-solutions
                (let [multiply-solutions (some
                                          #(let [trial (solve-n-deep (second %)
                                                                     (nth % 2)
                                                                     (- n 1))]
                                             (if trial
                                               (str trial "\n" (first %))
                                               nil))
                                          (list-for-recursion goal collection multiply-by-each " / "))]
                  (if multiply-solutions
                    multiply-solutions
                    (let [subtract-solutions (some
                                              #(let [trial (solve-n-deep (second %)
                                                                         (nth % 2)
                                                                         (- n 1))]
                                                 (if trial
                                                   (str trial "\n" (first %))
                                                   nil))
                                              (list-for-recursion goal collection subtract-by-each " + "))]
                      (if subtract-solutions
                        subtract-solutions
                        nil))))))))))))


(defn solve-one-deep [goal
                      collection]
  (solve-n-deep goal collection 1))


(defn solve [goal
             collection]
  (solve-n-deep goal
                collection
                (- (count collection) 1)))