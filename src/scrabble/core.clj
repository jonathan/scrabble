(ns scrabble.core
  (:gen-class :main true)
  (:import (java.io BufferedReader FileReader)))

; Pulled a lot of this from http://lethain.com/reading-file-in-clojure/

(defn process-file [file-name line-func letters]
  (with-open [rdr (BufferedReader. (FileReader. file-name))]
    (into [] (filter #(line-func letters %) (line-seq rdr)))
  )
)

(defn match-letters? [letters line]
  (loop [letter-freq (frequencies (clojure.string/lower-case letters))
        line-freq (frequencies (clojure.string/lower-case line))]
    (if (empty? line-freq)
      (if (not-any? neg? (vals letter-freq)) true false)
      (if (contains? letter-freq (key (first line-freq)))
        (recur (update-in letter-freq [(key (first line-freq))] - (line-freq (key (first line-freq)))) (dissoc line-freq (key (first line-freq))))
        false
      )
    )
  )
)

(defn main [letters]
  (println (process-file "/usr/share/dict/words" match-letters? letters)))

(defn -main [& args]
  (if (empty? (rest args))
    ((println "Usage: scrabble LETTERS")
    (System/exit 1))
   ((main (first (rest args)))
    (System/exit 0))))

