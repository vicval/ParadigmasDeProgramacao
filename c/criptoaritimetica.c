#include <string.h>
#include <stdio.h>
#include <stdlib.h>
 
// get the value of the next integer out of the string s
// quick and dirty
int next_int(char ** s) {
    char * q = *s;
    char * end = q+strlen(q);
    while(!isdigit(q[0]) && q < end)
        q++; // advance q to first digit
    char * p = q;
    while(isdigit(p[0]))
        p++; // advance q to character after last digit
    char tmp[p-q+1];
    strncpy(tmp, q, p-q);
    tmp[p-q] = '\0';
    *s = p; // advance s to where p is
//printf("-");
    return atoi(tmp);
}
 
// evaluate the simple expression q
// doesn't handle fancy stuff like precedence
int eval(char * q) {
    int val=0, len=strlen(q);
    char * t = q;
    while(1) {
//printf(t);
//se for decimal
        if(isdigit(t[0])) {
            val = next_int(&t);
        } else if (t[0]=='+') {
            val += next_int(&t);
        } else if (t[0]=='-') {
            val -= next_int(&t);
        } else if (t[0]=='*') {
            val *= next_int(&t);
        } else if (t[0]=='/') {
            val /= next_int(&t);
        } else if (t[0]=='\0') {
            break;
        } else {
            ++t;
        }
    }
    return val;
}
 
// solve the cryptarithmetic puzzle q
char * solve(char * q) {
// find c, the first unbound letter of q
    char c = 0;
    int i = 0, j = 0, len = strlen(q);
//se for uma letra, salva ela no char c e sai do for
    for (i=0; i<len; ++i) {
        if (isalpha(q[i])) {
            c = q[i];
//printf("%c\n",c);
//char as;
//scanf("%c",&as);
            break;
        }
    }
    if (c == 0) {
//printf("%c",c);
//char as;
//scanf("%c",&as);
// if there are no unbound letters
    // extract op1 and op2 operands
//end aponta para o final da string
    char * end = q+len;
//eq aponta para a parte da string q que contem a string ==
    char * eq = strstr(q, "==");
//declara op1 com o tamanho da primeira palavra da string q
//declara op2 com o tamanho da segunda palavra da string q
    char op1[eq-q+1], op2[end-eq-1];
//copia para op1 as letras da primeira palavra
    strncpy(op1, q, eq-q);
//copia para op2 as letras da segunda palavra
    strncpy(op2, eq+2, end-eq-2);
//coloca no uma caracter no final
    op1[eq-q] = '\0';
    op2[end-eq-2] = '\0';
    // evaluate op1 and op2
    int eval1 = eval(op1), eval2 = eval(op2);
    if(eval1 == eval2) { // solved!
        char * r = (char*)malloc(len*sizeof(char));
        strcpy(r, q);
//==================
//printf("r:%s ",r);
//modificar linha abaixo para verificar se o inicio de cada palavra está com resolucao 0
//no caso tinha 2 solucoes , uma m podia ser zero ou 1, tenho que retirar a solucao que da zero
//if(r[7]=='0' &&(r[6]==' ' || r[6]=='+'|| r[6]=='=')){
if(NULL != strstr(r, "=0")/*||NULL == strstr(r, "+0")||NULL == strstr(r, " 0")*/){
return 0;
}
//else{
return r;
//}
//==================
        return r;
    } else {
        return 0;
    }
    } else { // if c is the next unbound letter
        // find unused digits
        char dset[10] = {0,0,0,0,0,0,0,0,0,0};
        for (i=0; i<len; ++i)
//se for numero decimal, axo q ele verifica se a string inicial tem numero...e entao coloca aki
            if (isdigit(q[i]))
                dset[q[i]-'0'] = 1;
        for (i=0; i<10; ++i) {
            if (dset[i] == 0) { // if this digit i is unused
            // make a new string n with c replaced with i
                char * n = (char*)malloc(len*sizeof(char));
                for (j=0; j<len; ++j){
                    n[j] = (q[j] == c) ? i + '0' : q[j];
//printf("n:%c ",n[j]);
                }
//printf("\n");
//nao sei o a faz a linha acima
                char * r = solve(n);
                free(n);
                if (r){
                    //printf("r:%c",r[0]);
                    return r;
                }
            }
        }
    }
    return 0;
}
 
int main() {
    //char * query = "ABCDE*A==EEEEEE";
    char * query = "SEND+MORE==MONEY";
//char * query = "TOO+TOO+TOO+TOO==GOOD";
    char * result = solve(query);
    if(result) puts(result);
    else puts("No solution found.");
    return 0;
}

