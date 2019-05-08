(ns nocms.core)

(defn main []
  (let [c (.createElement js/document "div")]
    (set! (.-innerHTML c) "<p>custom</p>")
    (.appendChild (.-body js/document) c)))
