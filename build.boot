(set-env!
 :source-paths #{"src/cljs"}
 :resource-paths #{"public"}
 :dependencies '[[org.clojure/clojurescript "1.10.520"]
                 [adzerk/boot-cljs "2.1.5" :scope "test"]
                 [pandeiro/boot-http "0.8.3" :scope "test"]
                 [powerlaces/boot-figreload "0.5.14" :scope "test"]])

(require
 '[adzerk.boot-cljs :refer [cljs]]
 '[pandeiro.boot-http :refer [serve]]
 '[powerlaces.boot-figreload :refer [reload]])

(deftask min []
  (task-options! cljs {:optimizations :advanced})
  identity)

(deftask build []
  (comp (cljs)
        (target)))

(deftask run []
  (comp
   (watch)
   (reload)
   (build)
   (serve)))
