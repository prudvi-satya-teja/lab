
#include<stdio.h>

//  taken  a- 0, b - 1, c - 2, d - 3, ... , z - 25

int main() {
	char str[100], key[100], cipherText[100];
	
	scanf("%s", str);
	scanf("%s", key);
	
	int i = 0;
	while(str[i] != '\0') {
		char ch = str[i] - 'a';
		char ch2  = key[i] - 'a';
		
		cipherText[i] = ((ch ^ ch2) % 26) + 'a';
		i++;
	}
	cipherText[i] = '\0';
	printf("%s", cipherText);
	
}
