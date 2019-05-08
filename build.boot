(set-env!
 :source-paths #{"src/cljs"}
 :resource-paths #{"public"}
 :dependencies '[[org.clojure/clojurescript "1.10.520"]
                 [adzerk/boot-cljs "2.1.5" :scope "test"]])

(require
 '[adzerk.boot-cljs :refer [cljs]])

(deftask build []
  (comp (cljs)
        (target)))
