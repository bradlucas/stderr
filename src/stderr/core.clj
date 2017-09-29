(ns stderr.core
  (:gen-class)
  (:require [clojure.tools.cli :refer [cli]]))



;; Technique 1
;; Temporarily bind *out* to *err*
(defn write-stderr [msg]
  (binding [*out* *err*]
    (println msg)))


;; Technique 2
;; Call println on the *err* which is an instance of java.io.PrintWriter
;;
;; https://docs.oracle.com/javase/7/docs/api/java/io/PrintWriter.html
;;
;; stderr.core> *err*
;; #object[java.io.PrintWriter 0x58e29704 "java.io.PrintWriter@58e29704"]
(defn write-stderr2 [msg]
  (.println *err* msg))




(defn run-command
  [opts args banner]

  ;; write to STDOUT
  (println "Message to STDOUT")

  ;; write to STDERR
  (write-stderr "Message to STDERR")
  (write-stderr2 "Message to STDERR"))

(defn -main 
  [& args]
  (let [[opts args banner]
        (cli args
             ["-h" "--app-help" "Show help" :flag true :default false])]
    (if (:banner-help opts)
      (println banner)
      (run-command opts args banner))))
