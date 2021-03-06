(ns pwncheck.core
  (:gen-class)
  (:require [clj-http.client :as client] ;; HTTP client
            [clojure.string :as str])) ;; String handling

;; API docs: https://www.troyhunt.com/ive-just-launched-pwned-passwords-version-2/
(def apiurl "https://api.pwnedpasswords.com/range/")

(defn sha1
  "Calculates SHA1 hash in hex form"
  [input]
  (->> (.getBytes input  "UTF-8")
    (.digest (java.security.MessageDigest/getInstance "SHA1"))
    (java.math.BigInteger. 1)
    (format "%x")))

(defn makehashes
  "Breaks a hash digest into the two forms used"
  [passhash]
  {:fullhash (str/upper-case passhash) :shorthash (subs passhash 0 5)})

(defn apifetch
  "Check the API and split the results"
  [check]
  (->
  (client/get (str apiurl check))
  (get :body)
  (str/split-lines)))

(defn makematches
  "Call API and filter results check a password"
  [pass]
  (let [x (makehashes (sha1 pass)) ]
  (filter #(str/includes? % (subs (x :fullhash) 5))
     (apifetch (x :shorthash)))))

(defn cleanmatch
  [passmatch]
  (str "The password has been seen "
    (get (str/split passmatch #":") 1 0) " times"))

(defn checkmatches
  [matches]
  (if-let [f (first matches)]
  (cleanmatch f)
  "No passwords were found"))


(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (if-let [p (first args)]
  (println
    (checkmatches (makematches p)))
  (println "No password provided")))
