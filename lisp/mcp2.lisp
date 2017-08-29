;; Verifica se o estado é valorido
(defun movimento_possivel (estado)
  (or
    (and
      (> (length (second estado)) 0)
      (> (length (first estado)) (length (second estado)))
    )
    (and
      (> (length (fourth estado)) 0)
      (> (length (third estado)) (length (fourth estado)))
    )
  )
)

;; Verifica se o estado é o estado final.
(defun isgoal (estado)
  (and
    (= 0 (length (first estado)))
    (= 0 (length (second estado)))
    (= (length (third estado)) (length (fourth estado)))
    (= -1 (fifth estado))
  )
)

;; Verifica travessia
(defun travessia_possivel (estado canibal mission)
  (cond
    ;; Margem esquerda
    ((= (fifth estado) 1)
      (and (>= (length (first estado)) canibal)
           (>= (length (second estado)) mission))
    )
    ;; Margem direita
    (t
      (and (>= (length (third estado)) canibal)
           (>= (length (fourth estado)) mission))
    )
  )
)

;; Função auxiliar para adcionar na lista
(defun addlist (lista pos valor)
  (cond
    ((= 0 pos) lista)
    (t (addlist (cons valor lista) (1- pos) valor))
  )
)

;; Função auxiliar para reduzir da lista
(defun sublist (lista pos)
  (cond
    ((= 0 pos) lista)
    (t (sublist (rest lista) (1- pos)))
  )
)

;; Função para mover canibais e missionarios
;; pelas margens
(defun mover_de_margem (estado canibal mission)
  ;; Margem esquerda
  (if (= (fifth estado) 1)
    (list
      (sublist (first estado) canibal)
      (sublist (second estado) mission)
      (addlist (third estado) canibal 'c)
      (addlist (fourth estado) mission 'm)
      (* -1 (fifth estado))
    )
    ;; Margem direita
    (list
      (addlist (first estado) canibal 'c)
      (addlist (second estado) mission 'm)
      (sublist (third estado) canibal)
      (sublist (fourth estado) mission)
      (* -1 (fifth estado))
    )
  )
)

;; Faz a permutação de viagens possíveis utilizando
;; um tamanho de barco
(defun permuta (estado barco canibal mission viagens)
  (cond
    ((= 0 barco) viagens)
    ((= (1+ barco) mission) 
      (permuta estado (1- barco) (1- barco) 0 viagens)
    )
    (t
      (permuta estado barco (1- canibal) (1+ mission) 
        (cons
           (cond
             ((travessia_possivel estado canibal mission)
              (mover_de_margem estado canibal mission)
             )
             (t nil)
           )
           viagens
        )
      )
    )
  )
)

;; Verifica a veracidade de cada nó possivel
(defun no_possivel (no possivel aberto fechado)
  (cond
    ((null no) possivel) 
    ((and (not (null (first no)))
          (not (member (first no) aberto :test #'equal))
          (not (member (first no) fechado :test #'equal))
          (not (movimento_possivel (first no))))
      (no_possivel (rest no) (cons (first no) possivel) aberto fechado)
    )
    (t (no_possivel (rest no) possivel aberto fechado))
  )
) 

;; Busca todos os nos em que os missionarios não
;; tem desvantagem
(defun get_no (barco estado aberto fechado)
  (no_possivel (permuta estado barco barco 0 nil) nil (cons estado aberto) fechado)
)

;; Verifica todas as possibilidades e se existe solução.
(defun exec (barco aberto fechado caminho)
  (cond
    ((null aberto) "Não existe solução!")
    ((isgoal (first aberto)) (reverse (cons (first aberto) caminho)))
    (t
      (exec
        barco
        (append (get_no barco (first aberto) (rest aberto) fechado) (rest aberto))
        (cons (first aberto) fechado)
        (cons (first aberto) caminho)
      )
    )
  )
)

;; Inicializa as margens
(defun inicializa (pessoas qtd margem)
  (cond
    ((= 0 qtd) margem)
    (t (inicializa pessoas (1- qtd) (cons pessoas margem)))
  )
)

(defun imprime (resultado)
  (cond
    ((not (listp resultado)) (princ resultado))
    ((null resultado) nil)
    (t (princ (first resultado)) (terpri) (imprime (rest resultado)))
  )
)
  
(defun mcp (groupqtd barco)
  (imprime (exec
    barco 
    (list 
      (list
        (inicializa 'c groupqtd nil) 
        (inicializa 'm groupqtd nil) 
        nil
        nil
        1
      )
    ) 
    nil 
    nil
  ))
  nil
)
;; Teste: (mcp 3 2)