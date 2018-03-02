(ns pwncheck.core-test
  (:require [clojure.test :refer :all]
            [pwncheck.core :refer :all]))

(deftest shatest
  (testing "Generates SHA"
    (is (=
      (sha1 "password") "5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8"))))

(deftest makehashtest
  (testing "Splits the SHA1 correctly"
    (let [h (makehashes "abcdefgh")]
      (is (= (h :fullhash) "ABCDEFGH"))
      (is (= (h :shorthash) "abcde")))))

(deftest makematchtest
  (testing "Can run an API check and filter on a password"
  (is (= (count (makematches "password")) 1))))
