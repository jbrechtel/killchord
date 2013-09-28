(ns kill-chord.gpg-test
  (:require [clojure.test :refer :all]
            [kill-chord.gpg :refer :all]))

(def test-sig (slurp "test/kill_chord/test-sig.txt"))

(deftest finding-signatures
  (testing "can find a key-id in a public key"
    (let [expected-key-id "9593FA9883F7473A"
          actual-key-id   (parse-key-id-from-sig test-sig)]
    (is (= expected-key-id actual-key-id)))))
