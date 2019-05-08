(set-env!
 :source-paths #{"src/cljs"}
 :resource-paths #{"public"}
 :dependencies '[[org.clojure/clojurescript "1.10.520"]
                 [adzerk/boot-cljs "2.1.5" :scope "test"]
                 [pandeiro/boot-http "0.7.1-SNAPSHOT" :scope "test"]])

(require
 '[adzerk.boot-cljs :refer [cljs]]
 '[pandeiro.boot-http :refer [serve]])

(deftask min []
  (task-options! cljs {:optimizations :advanced})
  identity)

(deftask build []
  (comp (cljs)
        (target)))

(deftask run []
  (comp
   (watch)
   (build)
   (serve)))
