;; Define a matriz de pesos
(defvar *matrizpeso*
  '((0 15 20 25 30 10)
    (20 0 20 10 5 15)
    (20 14 0 13 12 20)
    (30 23 11 0 10 30)
    (30 10 10 10 0 35)
    (10 15 20 30 35 0)
  )
)

(defvar *distancias*)

;; Lista de cidades
(defvar *cities* '(0 1 2 3 4 5))

(defstruct (state) parent city visited cost)

;; Expande o nó
(defun expand (state menorcaminho-fn)
  (sort (loop with visited = (state-visited state)
              for city in (set-difference *cities* visited) ;; laço de repetição
              collect (make-state :parent state 
                                  :city city 
                                  :visited (cons city visited)
                                  :cost (+ (aref *distancias* (state-city state) city)
                                           (state-cost state))))
        menorcaminho-fn))
  
;; Faz a busca no grafo de cidades
(defun graphsearch (initial-state expand-fn final-fn menorcaminho-fn result-fn)
  (loop for open = (list initial-state) 
            then (merge 'list (cdr open) (funcall expand-fn (car open) menorcaminho-fn) menorcaminho-fn)
        when (funcall final-fn (car open))
        return (funcall result-fn (car open))))

;; Define o estado final/desejado
(defun final (state)
  (= (length (state-visited state)) (length *cities*)))

;; Verifica se o caminho é menor ou maior
(defun menorcaminho (state1 state2)
  (< (state-cost state1) (state-cost state2)))

;; Chega no resultado
(defun result (state &optional cities dis)
  (cond ((state-parent state)
         (result (state-parent state) (cons (state-city state) cities) (or dis (state-cost state))))
        ((format t "~%~%Menor caminho:~{~a ~}~%Distância do caminho: ~a~%" (cons (state-city state) cities) dis))))

;; Calcula a distancia entre cidades
(defun tsp (dist)
  (setf *distancias* (make-array (list (length dist) (length (car dist)))
                                     :initial-contents dist))
  (graphsearch (make-state :city 0 :visited '(0) :cost 0)
                        #'expand #'final #'menorcaminho #'result))