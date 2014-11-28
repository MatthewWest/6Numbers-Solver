(ns six-numbers-solver.web
  (:require-macros [hiccups.core :as h])
  (:require [domina :as dom]
            [hiccups.runtime :as hiccupsrt]
            [domina.events :as ev]
            [six-numbers-solver.calc :as calc]
            [cljs.reader :refer [read-string]]))

(defn print-solution [solution-string]
  ;;  (println solution-string)
  (dom/destroy! (dom/by-class "solution"))
  (doall (map
          #(dom/append! (dom/by-id "numberEntry")
                        (h/html [:div.solution %]))
          (clojure.string/split-lines solution-string))))

(defn web-solve []
  (let [goal (dom/value (dom/by-id "target"))
        num1 (dom/value (dom/by-id "num1"))
        num2 (dom/value (dom/by-id "num2"))
        num3 (dom/value (dom/by-id "num3"))
        num4 (dom/value (dom/by-id "num4"))
        num5 (dom/value (dom/by-id "num5"))
        num6 (dom/value (dom/by-id "num6"))
        coll
        (map
         read-string
         (filter
          #(not (clojure.string/blank? %))
          [num1 num2 num3 num4 num5 num6]))]
    (print-solution (calc/solve goal coll))))

(defn ^:export init []
  (when (and js/document
             (aget js/document "getElementById"))
    (ev/listen! (dom/by-id "calc") :click web-solve)
    ;;    (ev/listen! (dom/by-id "calc") :mouseover add-help)
    ;;    (ev/listen! (dom/by-id "calc") :mouseo ut remove-help)
    ))
