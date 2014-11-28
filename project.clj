(defproject six-numbers-solver "0.1.0-SNAPSHOT"
  :description "A program which solves the 6Numbers game of combining numbers to make a goal."
  :url "http://https://github.com/MatthewWest/6Numbers-Solver"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :min-lein-version "2.0.0"

  :source-paths ["src/clj"]

  :test-paths ["test/clj"]

  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/clojurescript "0.0-2371"]
                 [hiccups "0.2.0"]
                 [domina "1.0.2-SNAPSHOT"]
                 [org.clojure/clojure "1.6.0"]
                 [org.clojure/math.combinatorics "0.0.8"]
                 [org.clojure/math.numeric-tower "0.0.4"]]
  :main six-numbers-solver.core
  ; :aot [six-numbers-solver.core
  ;       six-numbers-solver.ui
  ;       six-numbers-solver.calc]
  :plugins [[lein-cljsbuild "0.3.0"]]
  :cljsbuild {:builds
              {:dev
               {;; clojurescript source code path
                :source-paths ["src/cljs"]

                ;; Google Closure Compiler options
                :compiler {;; the name of emitted JS script file
                           :output-to "resources/public/js/solver_dbg.js"

                           ;; minimum optimization
                           :optimizations :whitespace
                           ;; prettyfying emitted JS
                           :pretty-print true}}
               :pre-prod
               {;; same path as above
                :source-paths ["src/cljs"]

                :compiler {;; different JS output name
                           :output-to "resources/public/js/solver_pre.js"

                           ;; simple optimization
                           :optimizations :simple}}
               :prod
               {;; same path as above
                :source-paths ["src/cljs"]

                :compiler {;; different JS output name
                           :output-to "resources/public/js/solver.js"

                           ;; advanced optimization
                           :optimizations :advanced}}
               }}
  )
