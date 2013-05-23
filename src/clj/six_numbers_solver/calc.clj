(ns six-numbers-solver.calc)


(defn is-valid?
  "Return true or false for whether the number 1. exists, 2. is an integer, 3. is positive, 4. is less than 10 times the goal"
  [goal item]
  (and
   (not (nil? item))
   (= item (int item))
   (pos? item)
   (>=
    (* 10 goal)
    item)))



(defn in-list?
  "Return goal if it is in collection, else nil"
  [goal collection]
  (some
   #(= % goal)
   collection))


(defn divide-by-each
  "Return a collection containing the result of dividing the goal by each number in the collection in turn. nil = invalid result"
  [goal collection]
  (map
   #(if (is-valid? goal %)
      %
      nil)
   (map
    #(/ goal %)
    collection)))


(defn add-to-each
  "Return a collection containing the result of adding the goal to each number in the collection in turn. nil = invalid result"
  [goal collection]
  (map
   #(if (is-valid? goal %)
      %
      nil)
   (map
    #(+ goal %)
    collection)))


(defn subtract-by-each
  "Return a collection containing the result of subtracting the goal by each number in the collection. nil = invalid result"
  [goal collection]
  (map
   #(if (is-valid? goal %)
      %
      nil)
   (map
    #(- goal %)
    collection)))


(defn multiply-by-each
  "Return a collection containing for each number the result of subtracting the goal by it if it is valid, else nil"
  [goal collection]
  (map
   #(if (is-valid? goal %)
      %
      nil)
   (map
    #(* goal %)
    collection)))


(defn solved-by?
  "Return a collection containing the two numbers operated on to reach the goal, else nil"
  [operate-on-by-each ;; any of the preceding ...-by-each functions
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


(defn remove-index
  "Remove an element from the collection by its index"
  [i collection]
  (concat
   (take i collection)
   (drop (+ 1 i) collection)))



(defn list-for-recursion
  "Return a list with the elements needed for recursion

  There are three elements to the list this function returns:
  an equation showing how the current number reaches the target,
  the current number, and the original collection of numbers
  minus the number used already. Recursion works backward starting
  from the goal. For example, given [8 [1 2 3] divide-by-each ' / ']
  list-for-recursion would return a list of the results of dividing
  8 by first 1 then 2 then 3, each with new sets of arguments for
  the next level of recursion. The result: [['8 * 1 = 8' 8 [2 3]]
  ['4 * 2 = 8' 4 [1 3]] ['nil * 3 = 8' nil [1 2]]]. The result for
  3 is nil because it's filtered out by the divide-by-each function"
  [goal
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