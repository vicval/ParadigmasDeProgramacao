#include <stdio.h>

#define FALSE 0
#define TRUE 1

void apresenteSolucao(int tabuleiro[8][8]){
	int i, j, branca = TRUE;
	putchar('\n');
	for(i=0;i<8;i++){
		for(j=0;j<8;j++){
			if(tabuleiro[i][j]) printf(" R");
			else{
				if(branca) printf(" x");
				else printf(" x");
			branca = !branca;
			}
		}
		branca=!branca;
		printf("\n\n");
	}
}

int naoAmeacada(int tabuleiro[8][8], int linha, int coluna){
	int i, j, posicaoLegal = TRUE;
	i = linha - 1;
	while(i >=0 && posicaoLegal){
		posicaoLegal = !tabuleiro[i][coluna];
		i = i - 1;
	}
	i = i - 1;
	while(i >=0 && j < 8 && posicaoLegal){
		posicaoLegal = !tabuleiro[i][j];
		i = i - 1;
		j = j + 1;
	}
	i = linha - 1;
	j = coluna - 1;
	while(i >= 0 && j >= 0 && posicaoLegal){
		posicaoLegal = !tabuleiro[i][j];
		i = i - 1;
		j = j - 1;
	}
	return posicaoLegal;
}

int coloqueRainha(int tabuleiro[8][8], int linha){
	int coluna = 0, boaPosicao = FALSE;
	if(linha >= 8) return TRUE;
	else{
		while(coluna < 8 && !boaPosicao){
			tabuleiro[linha][coluna] = TRUE;
			if(naoAmeacada(tabuleiro,linha,coluna))
				boaPosicao = coloqueRainha(tabuleiro,linha+1);
			if(!boaPosicao){
				tabuleiro[linha][coluna] = FALSE;
				coluna = coluna + 1;
			}
		}
		return boaPosicao;
	}
}

int main(void){
	int i, j, tabuleiro[8][8];
	for(i=0; i<8; i++)
		for(j=0; j<8; j++) tabuleiro[i][j] = FALSE;
	if(coloqueRainha(tabuleiro,0)) apresenteSolucao(tabuleiro);
	else printf("Solucao nao encontrada\n");
	return 0;
}
