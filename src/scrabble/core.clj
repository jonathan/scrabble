(ns scrabble.core
  (:import (java.io BufferedReader FileReader)))

; Pulled a lot of this from http://lethain.com/reading-file-in-clojure/
(defn process-file [file-name line-func line-map]
  (with-open [rdr (BufferedReader. (FileReader. file-name))]
    (line-func line-map (line-seq rdr))))

(defn process-line [letters lines]
  (filter (fn [line]
            (= letters (frequencies line)))
          lines))

(defn main [letters]
  (process-file "/usr/share/dict/words" process-line (frequencies letters)))

(defn -main [& args]
  (if (rest args)
    (println (str "You passed in this value: " (rest args)))
    (println "Usage: cmdline VAlUE"))
    (main (rest args)))

