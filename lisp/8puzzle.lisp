;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;
;; 8PUZZLE.LISP - code for solving the 8-puzzle using search.
;; DFS and BFS tested; BFS seems to work; DFS takes too long to
;;    run to tell if it's working.
;; A* not debugged and not tested.  Left as an exercise for the reader. :-)
;;
;; Copyright (c) 2002, Marie desJardins
;;

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;; DATA STRUCTURES
;;;

(defstruct problem
  (initial-node)
  (goal-test)
  (operators))

(defstruct node
  (state)
  (goal-dist))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;; 8 PUZZLE DEFINITIONS
;;;

(defun 8puzz-goal-dist (node &aux (dist 0) (correct-tile 1))
  "Estimate the distance to the goal as the number of tiles out of place"
  ;; "correct-tile" is an index that keeps track of what tile should be
  ;; in each location; dist is the counter to keep track of the
  ;; total number of tiles out of place so far
  (loop for tile in (node-state node)
    do (progn (if (not (eq tile correct-tile)) (incf dist))
	      (incf correct-tile)))
  dist)

(defun 8puzz-goal-test (state)
  "Return T if state is the goal state for the 8-puzzle"
  ;; Could also just check to see if 8puzz-dist returns 0
  (equal state '(1 2 3 4 5 6 7 8 -))
  )

(defun move-blank-right (state &aux pos)
  "Operator to move the blank right.  If position POS of blank is not
     3, 6, or 9, swap blank and tile in position POS+1; otherwise, return nil
     (no new states generated)"
  (setf pos (index '- state))
  (if (not (or (eq pos 3) (eq pos 6) (eq pos 9)))
      (list (make-node :state (swap-tiles state pos (+ pos 1)))))
  )

(defun move-blank-up (state &aux pos)
  "Operator to move the blank up.  If position POS of blank is > 3,
     swap blank and tile in position POS-3; otherwise, return nil
     (no new states generated)"
  (setf pos (index '- state))
  (if (> pos 3)
      (list (make-node :state (swap-tiles state pos (- pos 3)))))
  )

(defun move-blank-down (state &aux pos)
  "Operator to move the blank down.  If position POS of blank is < 7,
     swap blank and tile in position POS+3; otherwise, return nil
     (no new states generated)"
  (setf pos (index '- state))
  (if (< pos 7)
      (list (make-node :state (swap-tiles state pos (+ pos 3)))))
  )

(defun move-blank-left (state &aux pos)
  "Operator to move the blank left.  If position POS of blank is not
     1, 4, or 7, swap blank and tile in position POS-1; otherwise, return nil
     (no new states generated)"
  (setf pos (index '- state))
  (if (not (or (eq pos 1) (eq pos 4) (eq pos 7)))
      (list (make-node :state (swap-tiles state pos (- pos 1)))))
  )

(defvar 8puzz-problem
  "The 8 puzzle problem"
  nil)

(setf 8puzz-problem
      (make-problem
       ;; A very easy state, with one tile out of place
       :initial-node (make-node :state '(1 2 3 4 5 6 7 - 8))
       :goal-test #'8puzz-goal-test
       :operators (list #'move-blank-up #'move-blank-down #'move-blank-left
			#'move-blank-right)))
(setf (node-goal-dist (problem-initial-node 8puzz-problem))
      (8puzz-goal-dist (problem-initial-node 8puzz-problem)))
      

(defun test8-dfs ()
  (General-Search 8puzz-problem #'DFS-queue))
(defun test8-bfs ()
  (General-Search 8puzz-problem #'BFS-queue))
(defun test8-a* ()
  (General-Search 8puzz-problem #'A*-queue))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;; GENERIC SEARCH FUNCTION

;; problem describes the start state, operators, goal test, and operator costs
;; q-fn is the queueing function (a comparator function that ranks two states)
;; general-search returns either a goal node or failure
(defun General-Search (problem q-fn
			       &aux nodes (visited nil))
  (setf nodes (list (make-node :state (node-state
				       (problem-initial-node problem)))))
  (loop do
    (progn
      (if (null nodes) (return-from General-Search 'failure))
      (setf node (car nodes))
      (setf nodes (cdr nodes))
      ;; Don't test an already visited node; just loop again
      (when (not (member (node-state node) visited :test #'equal))
	(format t "Testing ~s (~s nodes visited, ~s nodes open)~%"
		(node-state node) (length visited) (length nodes))
	(if (funcall (problem-goal-test problem)
		     (node-state node))
	    (return-from General-Search 
	      (values node (length visited) (length nodes)))
	  (progn (push (node-state node) visited)
		 (setf nodes 
		       (funcall q-fn nodes
				(Expand node 
					(problem-operators problem)
					visited)))))))))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;; EXPAND FUNCTION FOR 8-PUZZLE
;;;

(defun Expand (node operators visited)
  (remove-if #'(lambda(n) (member n visited :test #'equal))
	     (apply #'nconc (mapcar #'(lambda (op) 
					(funcall op (node-state node)))
				    operators))))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;   QUEUING FUNCTIONS
;;;

(defun DFS-queue (nodes new-nodes)
  "Destructively inserts new-nodes list into the node list for a DFS search
     (new-nodes are prepended to node list)"
  (nconc new-nodes nodes))

(defun BFS-queue (nodes new-nodes)
  "Destructively inserts new-nodes list into the node list for a BFS search
    (new-nodes are appended to node list)"
  (nconc nodes new-nodes))

(defun A*-queue (nodes new-nodes)
  "Non-destructively inserts new-nodes into the node list for an A* search
     (new-nodes are inserted in increasing order of estimated distance
     to the goal)"
  (loop for new in new-nodes 
    do (setf nodes (insert-node-ordered new nodes))))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;; UTILITY FUNCTIONS
;;;

(defun insert-node-ordered (new nodes)
  "Non-destructively insert NEW into a node list, in increasing order
     of estimated distance to the goal.  (Note that NODES is assumed
     to already be ordered, by construction.)"
  (cond ((null nodes) (list new))
	((< (goal-dist new) (goal-dist (car nodes))) 
	 (cons new nodes))
	(t (cons (car nodes) (insert-node-ordered new (cdr nodes))))))

(defun index (el l &optional (pos 1))
  "Return the index (position) of el in l"
  (if (null l) (error "Can't find ~s in function INDEX~%" el))
  (if (eq el (car l))
      pos
    (index el (cdr l) (incf pos))))

(defun swap-tiles (state p1 p2 &aux temp state2)
  "Swap the p1'th and p2'th elements of state in a new copy;
     indices start at 1, not 0, so have to subtract 1 for 'nth' function"
  (setf state2 (copy-tree state))
  (decf p1) 
  (decf p2)
  (setf temp (nth p1 state2))
  (setf (nth p1 state2) (nth p2 state2))
  (setf (nth p2 state2) temp)
  state2)
	
