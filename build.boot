(set-env!
 :source-paths #{"src/cljs"}
 :resource-paths #{"public"}
 :dependencies '[[org.clojure/clojurescript "1.10.520"]
                 [adzerk/boot-cljs "2.1.5" :scope "test"]
                 [pandeiro/boot-http "0.8.3" :scope "test"]
                 [powerlaces/boot-figreload "0.5.14" :scope "test"]
                 [reagent "0.8.1"]
                 [cljs-http "0.1.46"]
                 [zaeny/boot-atomizer "0.1.1"]])

(require
 '[adzerk.boot-cljs :refer [cljs]]
 '[pandeiro.boot-http :refer [serve]]
 '[powerlaces.boot-figreload :refer [reload]]
 '[zaeny.boot-atomizer :refer [atomizer]])

(deftask min []
  (task-options! cljs {:optimizations :advanced})
  identity)

(deftask build []
  (comp
   (atomizer :find-class "src/cljs/nocms"
             :path-atomizer "./node_modules/atomizer/bin/atomizer")
   (cljs :compiler-options {:install-deps true
                            :npm-deps {"@material-ui/core" "3.9.3"
                                       "react" "16.8.6"
                                       "react-dom" "16.8.6"}})
   (target)))

(deftask run []
  (comp
   (watch)
   (reload)
   (build)
   (serve)))
