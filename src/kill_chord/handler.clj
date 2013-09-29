(ns kill-chord.handler
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [kill-chord.templates :as templates]
            [clj-time.core :as time]
            [clj-time.format :as time-format]
            [clojure.java.io :as io]))

(defroutes app-routes
  (GET "/" [] (clojure.string/join (templates/index-template "Kill Chord")))
  (GET "/heartbeat" [] (time-format/unparse (time-format/formatters :rfc822) (time/now)))
  (POST "/heartbeat" [:as r]
    (with-open [rdr (io/reader (:body r))]
      (clojure.string/join "\n" (line-seq rdr))))
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (handler/site app-routes))
