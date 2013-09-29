(defproject kill_chord "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [compojure "1.1.5"]
                 [enlive "1.1.4"]
                 [com.datomic/datomic-free "0.8.4159"]
                 [ring/ring-jetty-adapter "1.2.0"]
                 [clj-time "0.6.0"]]
  :plugins [[lein-ring "0.8.5"]]
  :ring {:handler kill-chord.handler/app})
