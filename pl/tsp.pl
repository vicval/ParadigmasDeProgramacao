/* CASO TESTE */
/* Definição de cidades */
city(a).
city(b).
city(c).
city(d).
city(e).
city(f).
city(g).

/* Definição de caminhos da cidade a */
c(a,b, 211).
c(a,c, 2690).
c(a,d, 3119).
c(a,f,3088).
c(a,e, 2632).
c(a,g,442).

/* Definição de caminhos da cidade b */
c(b,c,2485).
c(b,d,2925).
c(b,f,2894).
c(b,e,2427).
c(b,g,237).

/* Definição de caminhos da cidade c */
c(c,d,1347).
c(c,f,1487).
c(c,e,114).
c(c,g,2350).

/* Definição de caminhos da cidade d */
c(d,f,175).
c(d,e,1460).
c(d,g,2819).

/* Definição de caminhos da cidade f */
c(f,e,1602).
c(f,g,2788).

/* Definição de caminhos da cidade e */
c(e,g,2279).

/* Definição do custo de viagem */
cost(A,B,V):- 
	c(A,B,V);c(B,A,V).

/* Lógica de solução */
perm([],[]).
perm([A|S],[A|T]):-
	perm(S,T).
perm([A|S],[B|T]):-
	perm(S,T1), exchange(A,B,T1,T).

exchange(A,B,[B|T],[A|T]).
exchange(A,B,[C|S],[C|T]):-
	exchange(A,B,S,T).

cities(P):-
	setof(C,city(C),P).

viajar([C|W]):-
	cities([C|P]),perm(P,W).

ccost([A|R],V):-
	ccost([A|R],V,A).

ccost([A],V,F):-
	cost(A,F,V),!.

ccost([A,B|R],V,F):-
  cost(A,B,V1),
  ccost([B|R],V2,F),
  V is V1+V2.

itinerary(W,V):- 
	viajar(W),ccost(W,V).

best([K-P|R],X):-
	best(R,L-Q),better(K-P,L-Q,X),!.

best([X],X).

better(K-P,L-_,K-P):
	-K<L,!.
better(_,X,X).

solve(X):-
	setof(V-W,itinerary(W,V),B),best(B,X).