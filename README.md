# stderr

An example Clojure program showing how to write to STDERR.

See the core.clj file for two examples of how to write a message to STDERR.

```
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
```


## Usage

Run via lein and pipe STDOUT to one file and STDERR to another

```
$ lein run >stdout.log 2>stderr.log
```

Verify that each file has different content

```
$ cat stdout.log
Message to STDOUT

$ cat stderr.log
Message to STDERR
Message to STDERR
```



## License

Copyright © 2017 Brad Lucas

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
