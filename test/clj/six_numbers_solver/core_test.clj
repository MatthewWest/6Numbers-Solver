(ns six-numbers-solver.core-test
  (:gen-class)
  (:use clojure.test
        six-numbers-solver.core))

(deftest core-to-cmd-1
  (testing "When -main is called with a -c flag, it should pass to the cmd interface"
    (is (= "1 + 3 = 4\n4 * 2 = 8" (with-out-str (-main '-c "[8 [1 2 3]]"))))))