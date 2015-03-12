(ns webapp.handler
  (:use [hiccup.core]
        [hiccup.form]
        [hiccup.page]
        [hiccup.element]
        [ring.util.response]
        [ring.middleware.session])
  (:require [compojure.core :refer :all]
            [compojure.handler :as handler]
            [compojure.route :as route]
            [cheshire.core :refer :all]
            [webapp.processhandler :as ph]))


(defn stream-stuff [req]
  {:status 200
   :headers {"Content-Type" "text/event-stream"}
   :body (map #(str "data:" % "\n\n" ) (ph/ext-process-output-stream ph/PROCESS-BIN))})


(defroutes app-routes
  (GET "/" [] (html5
               [:head
                (include-js "https://code.jquery.com/jquery-2.1.3.min.js")
                (include-js "https://cdnjs.cloudflare.com/ajax/libs/flot/0.8.2/jquery.flot.min.js")
                (include-js "app.js")
                (include-css "style.css")]
               [:body
                [:h2 "some numbers"]
                [:div#pippo]]))
  (GET "/somestream" [] stream-stuff)
  (route/resources "/")
  (route/not-found "Not Found"))


(def app
  (handler/site app-routes))
