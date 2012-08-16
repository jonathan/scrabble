(ns scrabble.test.core
  (:use [scrabble.core])
  (:use [midje.sweet]))

(fact "match-letters?"
  (fact "with line 'a'"
    (fact "should be true with letters 'a'"
      (match-letters? "a" "a") => true)
    (fact "should be false with letters 'b'"
      (match-letters? "a" "b") => false)
  )
  (fact "with line 'happy'"
    (fact "should be true with letters 'h' 'a' 'p' 'p' 'y'"
      (match-letters? "happy" "happy") => true)
    (fact "should be true with letters 'h' 'a' 'p' 'p' 'y' 's'"
      (match-letters? "happys" "happy") => true)
    (fact "should be true with capital letters 'H' 'a' 'P' 'p' 'y' 's'"
      (match-letters? "HaPpys" "happy") => true)
    (fact "should be false with letter 'b'"
      (match-letters? "happy" "b") => false)
  )
)
