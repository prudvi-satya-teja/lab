#include<stdio.h>

int main() {
    char arr[500];
    int key;

    scanf("%s", arr);
    scanf("%d", &key);

    int i= 0;
    while(arr[i] != '\0') {
        char ch = arr[i];
        if(ch >= 'a' && ch <= 'z') {
        	arr[i] = ((ch - 'a' - key + 26) % 26) + 'a';
		}
		else if(ch >= 'A' && ch <= 'Z'){
			arr[i] = ((ch - 'A' - key + 26) % 26) + 'A';
		}
		else if(ch >= '0' && ch <= '9') {
			arr[i] = ((ch - '0' - key + 10) % 10) + '0';
		}
		else {
			printf("invalid");
			return 0;
		}
        i++;
    }

    printf("msg is %s", arr);

}
