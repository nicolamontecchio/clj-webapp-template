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
            [cheshire.core :refer :all]))

(defn page-login-post [post-params session]
  (let [uid (get post-params :userid)]
    (assoc (response (html [:p "login name is " [:b  uid]])) :session (assoc session :userid uid))))

(defroutes app-routes
  (GET "/" [] "Hello World")
  ;; (GET "/login" [] (page-login-get))
  ;; (POST "/login" {post-params :params session :session}  (page-login-post post-params session))
  ;; (GET "/profile" {session :session} (page-profile nil session))
  (GET "/somestream" [] "qwe")
  (GET "/profile/:userid" {userid :userid session :session} (page-profile userid session))
  (route/resources "/")
  (route/not-found "Not Found"))



(def app
  (handler/site app-routes))
