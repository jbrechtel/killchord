(ns kill-chord.gpg
  (require [clojure.java.shell :as sh]))

(defmacro with-keyring [[public-key keyring-path] & body]
  `(let [keyring-file#         (java.io.File/createTempFile "keyring" ".gpg")
         ~'keyring-path        (.getAbsolutePath keyring-file#)
         gpg-opts#             ["gpg" "--import" "--no-default-keyring"
                                "--keyring" ~keyring-path
                                :in ~public-key]
         import-result#        (apply sh/sh gpg-opts#)]
     (let [retval# (do ~@body)]
       (.delete keyring-file#)
       retval#)))

(defn parse-key-id-from-sig [sig]
  (let [gpg-result (sh/sh "gpg" "--list-packets" :in sig)
        output     (:out gpg-result)
        regex      #"(?m)^:signature packet:.* keyid ([0-9,A-F]{16})$"]
    (last (re-find regex output))))

(defn parse-key-id-from-key [key]
  (with-keyring [key keyring-path]
    (let [list-keys-result        (sh/sh "gpg" "--fingerprint" "--no-default-keyring" "--keyring" keyring-path)
          regex                    #"(?m)^pub.*$\s+Key fingerprint = (([0-9,A-F]{4}\s*?){10})"
          fingerprint-match        (re-find regex (:out list-keys-result))]
      (clojure.string/replace (nth fingerprint-match 1) " " ""))))

(defn verify-signature [message public-key]
  (with-keyring [public-key keyring-path]
    (let [gpg-verify-opts      ["gpg" "--no-default-keyring"
                                "--keyring" keyring-path
                                "--verify"
                                :in message]
          verify-results       (apply sh/sh gpg-verify-opts)]
        (= 0 (:exit verify-results)))))
