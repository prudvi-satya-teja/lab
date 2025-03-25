#include<stdio.h>


char secret[26][2] = {
	{'a', 'b'}, {'b', 'c'}, {'c', 'd'}, {'d', 'e'}, {'e', 'f'},
    {'f', 'g'}, {'g', 'h'}, {'h', 'i'}, {'i', 'j'}, {'j', 'k'},
    {'k', 'l'}, {'l', 'm'}, {'m', 'n'}, {'n', 'o'}, {'o', 'p'},
    {'p', 'q'}, {'q', 'r'}, {'r', 's'}, {'s', 't'}, {'t', 'u'},
    {'u', 'v'}, {'v', 'w'}, {'w', 'x'}, {'x', 'y'}, {'y', 'z'},
    {'z', 'a'}};
    
char get_char(char ch) {
	    int i = 0;
		for(i=0; i<26; i++) {
			if(secret[i][0] == ch) {
				return secret[i][1];
			}
		}
		return '0';
}

int main() {
	char str[20];
	
	scanf("%s", str);
	
	int i = 0;
	while(str[i] != '\0') {
		char ch = str[i];
		char encrypt = get_char(ch);
		
		if(encrypt == '0') {
			printf("enter valid message");
			return 1;
		}
		str[i]  = encrypt;
		i++;
	}
	printf("%s", str);
	return 0;
}
