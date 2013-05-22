(ns six-numbers-solver.cmd-test
  (:gen-class)
  (:use clojure.test
        six-numbers-solver.cmd))

(deftest parse-arguments-1
  (testing "Parse arguments of the form [goal [collection]]"
    (is (= [5 [6 2 34]] (parse-arguments "[5 [6 2 34]]")))))

(deftest parse-arguments-2
  (testing "Edge case for no target number"
    (is (= nil (parse-arguments "[ [6 2 34]]")))))

(deftest parse-arguments-3
  (testing "Edge case for no collection"
    (is (= nil (parse-arguments "[5]")))))

(deftest parse-arguments-4
  (testing "Edge case for collection of size 0"
    (is (= [5 []] (parse-arguments "[5 []]")))))

(deftest answer-out-1
  (testing "Send the solution to stdout"
    (is (= "2 + 3 = 5\n5 + 4 = 9\n9 * 3 = 27" (with-out-str (answer-out "2 + 3 = 5\n5 + 4 = 9\n9 * 3 = 27"))))))

(deftest answer-out-2
  (testing "If there is no solution, output false"
    (is (= "false" (with-out-str (answer-out nil))))))

(deftest main-1
  (testing "Invalid arguments should result in false"
    (is (= "false" (with-out-str (main "[1.5 [3 2]]"))))))

(deftest main-2
  (testing "Valid arguments should return the correct solution"
    (is (= "1 + 3 = 4\n4 * 2 = 8" (with-out-str (main "[8 [1 2 3]]"))))))

