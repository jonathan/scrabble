(ns scrabble.core
  (:import (java.io BufferedReader FileReader)))

; Pulled a lot of this from http://lethain.com/reading-file-in-clojure/
(defn process-file [file-name line-func line-map]
  (with-open [rdr (BufferedReader. (FileReader. file-name))]
    (reduce line-func line-map (line-seq rdr))))

; Note use of fnil below - returns 0 if nil is passed to inc (avoids null pointer exception)
(defn char-cnt [s]
  (reduce
    (fn [m k]
      (update-in m [k] (fnil inc 0)))
  {}
  (seq s)))

(defn process-line [letters line]
  (if (= letters (char-cnt line))
    (println ("found" line))
    (line)))

; Got this from: http://clojuredocs.org/clojure_core/clojure.core/update-in
(defn main [letters]
  (process-file "/usr/share/dict/words" process-line (char-cnt letters)))

(defn -main [& args]
  (if (rest args)
    (println (str "You passed in this value: " (rest args)))
    (println "Usage: cmdline VAlUE"))
    (main (rest args)))

