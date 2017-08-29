//============================================================
consult(nome_do_arquivo).
puzzle1(N1,N2,N),sum(N1,N2,N).


Programa

sum(N1,N2,N):-
 sum1(N1,N2,N,0,0,[0,1,2,3,4,5,6,7,8,9],_),
 verificaZero(N).

sum1([],[],[],C,C,Digits,Digits).

sum1([D1|N1],[D2|N2],[D|N],C1,C,Digs1,Digs):-%/*D1|N1 REPRESENTAM:D1= CABECA DA LISTA, E N1 O CORPO DELA, ISTO É O RESTO
 sum1(N1,N2,N,C1,C2,Digs1,Digs2),
 digitsum(D1,D2,C2,D,C,Digs2,Digs).

digitsum(D1,D2,C1,D,C,Digs1,Digs):-
 del(D1,Digs1,Digs2),%a funcação del tem opapel de instanciar D1,D2 E D caso elas não estejam instanciadas ainda. Digs1 é uma lista cons os valores que d1 pode assumir, e Digs2, é uma lista com os valores que as variaveis ainda não instanciadas podem assumir.
 del(D2,Digs2,Digs3),
 del(D,Digs3,Digs),
 S is D1+D2+C1, %C1 é o vai umque veio da direita
 D is S mod 10,%D
 C is S // 10.% O SIMBOLO // É DIVISÃO DE INTEIRO E O / É DIVISÃO DE REAL(). essa linha calcula o "vai um para a esquerda"

del(A,L,L):-
 nonvar(A),!.

del(A,[A|L],L).%a variavel D,D1,D2 assumem valor da cabeça da lista [a|l] e retorna uma outra lista sem o a.
del(A,[B|L],[B|L1]):-%funcao recursiva que possibilita que D, D1,D2 assumem valores que estao no meio da lista de Digs.
 del(A,L,L1).


%retorna verdade se a cabeça da lista é diferente de zero.
verificaZero([A|L]):-
A \== 0.


% Some puzzles
puzzle1([D,O,N,A,L,D],[G,E,R,A,L,D],[R,O,B,E,R,T]).
puzzle2([0,S,E,N,D], [0,M,O,R,E],[M,O,N,E,Y]).
puzzle3([E], [E],[E]).
