(ns kill-chord.handler
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [kill-chord.templates :as templates]
            [clj-time.core :as time]
            [clj-time.format :as time-format]))

(defroutes app-routes
  (GET "/" [] (clojure.string/join (templates/index-template "Kill Chord")))
  (GET "/heartbeat" [] (time-format/unparse (time-format/formatters :rfc822) (time/now)))
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (handler/site app-routes))
