(ns six-numbers-solver.calc-test
  (:gen-class)
  (:use clojure.test
        six-numbers-solver.calc))

(deftest valid-test-1
  (testing "Goal: 65, Valid?: 2"
    (is (= true (is-valid? 65 2)))))

(deftest valid-test-2
  (testing "Decimals and ratios are not valid."
    (is (= false (is-valid? 65 2.5)))))

(deftest valid-test-3
  (testing "Numbers more than ten times the goal number are not valid."
    (is (= false (is-valid? 2 25)))))

(deftest valid-test-4
  (testing "Negative numbers are not valid."
    (is (= false (is-valid? 65 -3)))))

(deftest divide-by-each-test-1
  (testing "Invalid results equal nil"
    (is (= [4 nil 12] (divide-by-each 12 [3 5 1])))))

(deftest divide-by-each-test-2
  (testing "Square roots don't behave differently"
    (is (= [5 5 nil nil] (divide-by-each 25 [5 5 6 7])))))

(deftest subtract-by-each-test
  (testing "Invalid (negative) results equal nil"
    (is (= [nil 2 nil] (subtract-by-each 4 [5 2 6])))))

(deftest multiply-by-each-test
  (testing "Invalid (>10 * goal) results equal nil"
    (is (= [6 nil 8] (multiply-by-each 2 [3 156 4])))))

(deftest solve-one-deep-list
  (testing "When the number is in the list, return a solution"
    (is (= "125 = 125" (solve-one-deep 125 [125 5 7 6])))))

(deftest solve-one-deep-multiplication
  (testing "Handle perfect squares normally"
    (is (= "5 * 5 = 25" (solve-one-deep 25 [1 5 5 6 7])))))

(deftest solve-one-deep-subtraction-1
  (testing "Subtraction is not commutative, must display the correct order"
    (is (= "36 - 2 = 34" (solve-one-deep 34 [36 2])))))

(deftest solve-one-deep-subtraction-2
  (testing "Subtraction order when the original list is in the opposite order"
    (is (= "9 - 4 = 5" (solve-one-deep 5 [2 4 9])))))

(deftest solve-one-deep-addition
  (testing "Addition works with extra terms"
    (is (= "22 + 5 = 27" (solve-one-deep 27 [22 5 3])))))

(deftest solve-one-deep-division
  (testing "Division works successfully"
    (is (= "18 ÷ 9 = 2" (solve-one-deep 2 [9 18])))))

(deftest solved-by?-addition
  (testing "Addition uses each number only once, not adding a number to itself"
    (is (= nil (solved-by? subtract-by-each 20 [10 2])))))

(deftest solved-by?-multiplication
  (testing "Multiplication uses each number only once, not multiplying a number by itself"
    (is (= nil (solved-by? divide-by-each 25 [5 20])))))

(deftest solved-by?-division
  (testing "Division is not commutative. It must return the numbers in the correct order."
    (is (= [90 3] (solved-by? multiply-by-each 30 [3 90 60])))))

(deftest solved-by?-subtraction
  (testing "Subtraction is not commutative. It must return the numbers in the correct order."
    (is (= [9 4] (solved-by? add-to-each 5 [2 4 9])))))

(deftest remove-index-1
  (testing "Remove an element by its index when index = 0"
    (is (= [1 2 3 4 5] (remove-index 0 (range 6))))))

(deftest list-for-recursion-1
  (testing "list-for-recursion returns a list of lists of the form ['Operation required to reach' newgoal (remaining-collection)]"
    (is (= '(["5 + 2 = 7" 5 (3 1)] ["4 + 3 = 7" 4 (2 1)] ["6 + 1 = 7" 6 (2 3)]) (list-for-recursion 7 [2 3 1] subtract-by-each " + ")))))

(deftest list-for-recursion-2
  (testing "list-for-recursion to return a list of lists for possible continuations by its operation"
    (is (= [["8 * 1 = 8" 8 [2 3]] ["4 * 2 = 8" 4 [1 3]]] (list-for-recursion 8 [1 2 3] divide-by-each " * ")))))

(deftest solve-n-deep-1
  (testing "Find a solution using up to n - 1 numbers"
    (is (= "3 * 4 = 12\n12 + 1 = 13" (solve-n-deep 13 [3 4 1] 2)))))

(deftest solve-n-deep-2
  (testing "Return nil if there is not a solution within n levels"
    (is (= nil (solve-n-deep 8 [1 2 3] 1)))))

(deftest solve-n-deep-3
  (testing "Return the solution if it is within n levels"
    (is (= "1 + 3 = 4\n4 * 2 = 8" (solve-n-deep 8 [1 2 3] 2)))))

(deftest solve-n-deep-4
  (testing "Return higher-level solutions also"
    (is (= "1 + 3 = 4\n4 * 2 = 8\n8 * 2 = 16" (solve-n-deep 16 [1 2 2 3] 3)))))

(deftest solve-1
  (testing "Solve finds a solution from a list and a goal"
    (is (not (nil? (solve 381 [75 9 6 3 8 5]))))))

(deftest solve-2
  (testing "Solve finds a solution from a list and a goal"
    (is (not (nil? (solve 532 [75 7 1 8 50 6]))))))

(deftest solve-3
  (testing "Solve finds a solution from a list and a goal"
    (is (not (nil? (solve 616 [8 75 3 10 4 2]))))))

(deftest solve-4
  (testing "Solve finds a solution from a list and a goal"
    (is (not (nil? (solve 231 [2 75 5 25 3 8]))))))

(deftest solve-5
  (testing "Solve finds a solution from a list and a goal"
    (is (not (nil? (solve 350 [6 2 1 9 50 10]))))))

(deftest solve-6
  (testing "Solve finds a solution from a list and a goal"
    (is (not (nil? (solve 830 [75 3 10 2 8 7]))))))
