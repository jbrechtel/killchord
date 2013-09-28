(ns kill-chord.gpg
  (require [clojure.java.shell :as sh]))

(defn parse-key-id-from-sig [sig]
  (let [gpg-result (sh/sh "gpg" "--list-packets" :in sig)
        output     (:out gpg-result)
        regex      #"(?m)^:signature packet:.* keyid ([0-9,A-F]{16})$"]
    (last (re-find regex output))))

(defn parse-key-id-from-key [key]
  "foo")

(defn verify-signature [public-key message]
  false)
