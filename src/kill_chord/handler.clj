(ns kill-chord.handler
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [kill-chord.templates :as templates]))

(defroutes app-routes
  (GET "/" [] (clojure.string/join (templates/index-template "Kill Chord")))
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (handler/site app-routes))
