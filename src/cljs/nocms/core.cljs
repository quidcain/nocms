(ns nocms.core
  (:require [reagent.core :as r]
            [cljs-http.client :as http]
            [cljs.core.async :refer [<!]]
            ["@material-ui/core" :as mui])
  (:require-macros [cljs.core.async.macros :refer [go]]))

(defn get-data
  [f]
  (go (let [response (<! (http/get
                          "http://localhost:3009"
                          {:with-credentials? false}))]
        (f (:body response)))))

(defn render-list
  [items]
  [:ul
   (for [item items]
     [:li "Item " (:name item)])])

(defn pulling
  []
  (let [items (r/atom nil)
        handle-response (fn [] (get-data #(reset! items %)))]
    (fn []
      [:div.container {:class ["Mih(100px) Bgc(#e2d3c3) Bdrs(3px)"
                               "Bdw(4px) Bds(s) Bdc(#f7d2aa)"
                               "P(10px) D(f) Fld(c) Ai(c)"
                               "shadow"]}
       [render-list @items]
       [:input {:type :button
                :value "Get data"
                :class "D(b) Mt(a)"
                :on-click handle-response}]])))

(def mui-button (r/adapt-react-class (.-Button mui)))


(defn main []
  (r/render [mui-button {:variant "contained"}
             "Hello"]
            (js/document.getElementById "app")))
