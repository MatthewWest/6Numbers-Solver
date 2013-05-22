(ns six-numbers-solver.web
  (:require [domina :as dom]
            [domina.events :as ev]
            [six-numbers-solver.calc :refer [solve]]))

(defn web-solve []
  )

(defn ^:export init []
  (when (and js/document
             (aget js/document "getElementById"))
    (ev/listen! (dom/by-id "calc") :click web-solve)
;;    (ev/listen! (dom/by-id "calc") :mouseover add-help)
;;    (ev/listen! (dom/by-id "calc") :mouseout remove-help)
    ))