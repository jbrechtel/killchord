(ns kill-chord.gpg-test
  (:require [clojure.test :refer :all]
            [kill-chord.gpg :refer :all]))

(def test-sig (slurp "test/kill_chord/test-sig.txt"))
(def test-key (slurp "test/kill_chord/test-key.txt"))
(def test-sig2 (slurp "test/kill_chord/test-signed-message.txt"))
(def test-key2 (slurp "test/kill_chord/test-signed-message-public-key.txt"))
(def test-sig-bad (slurp "test/kill_chord/test-signed-message-bad.txt"))

(deftest finding-signatures
  (testing "can find a key-id in a signed message"
    (let [expected-key-id "9593FA9883F7473A"
          actual-key-id   (parse-key-id-from-sig test-sig)]
    (is (= expected-key-id actual-key-id))))
  (testing "can find a key-id from a public key"
    (let [expected-key-id "170B3396DD7055021F806B513CFFD6AF57CEE3F6"
          actual-key-id   (parse-key-id-from-key test-key)]
    (is (= expected-key-id actual-key-id)))))

(deftest cryptographic-operations
  (testing "can validate signature given public key"
    (is (verify-signature test-sig2 test-key2)))
  (testing "verify-signature returns false for signatures that do not validate"
    (is (not (verify-signature test-sig-bad test-key2)))))
