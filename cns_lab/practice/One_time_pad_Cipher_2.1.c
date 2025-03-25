#include<stdio.h>
#include<string.h>

int main() {
	char str[100], randomKey[100], cipher[100];

	gets(str);
	gets(randomKey);
	
	if(strlen(str) > strlen(randomKey)) {
		printf("key is less size than text");
		return 0;
	}
	int i = 0;
	for(i=0; i<strlen(str); i++) {
		char ch1 =str[i] - 'a';
		char ch2 = randomKey[i] - 'a';
		
		int n = (ch1 + ch2);
		if(n >= 26) n = n - 26;
		cipher[i] = n + 'a';
	}
	cipher[i] = '\0';
	printf("%s", cipher);
}
