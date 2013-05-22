(ns six-numbers-solver.create-test
  (:gen-class)
  (:use clojure.test
        six-numbers-solver.create))

(deftest is-valid?-1
  (testing "Negative numbers are not valid"
    (is (= nil (is-valid? -3)))))

(deftest is-valid?-2
  (testing "Ratios are not valid"
    (is (= nil (is-valid? 2/3)))))

(deftest is-valid?-3
  (testing "Numbers > 1000 are not valid"
    (is (= nil (is-valid? 1500)))))

(deftest is-valid?-4
  (testing "55 is a valid number"
    (is (= 55 (is-valid? 55)))))