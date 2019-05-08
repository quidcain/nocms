(set-env!
 :source-paths #{"src/cljs"}
 :resource-paths #{"public"}
 :dependencies '[[org.clojure/clojurescript "1.10.520"]
                 [adzerk/boot-cljs "2.1.5" :scope "test"]
                 [pandeiro/boot-http "0.7.1-SNAPSHOT" :scope "test"]])

(require
 '[adzerk.boot-cljs :refer [cljs]]
 '[pandeiro.boot-http :refer [serve]])

(deftask build-min []
  (comp (cljs :optimizations :advanced)
        (target)))

(deftask build []
  (comp (cljs)
        (target)))

(deftask run []
  (comp
   (serve)
   (watch)))
