clude<string.h>

int main() {
    char arr[500];
    int key;

    scanf("%s", arr);
    scanf("%d", key);

    int i= 0;
    char decrpt[500];
    while(arr[i] != '\0') {
        char ch = arr[i];
        char deCh = (arr[i] - key) % 26;
        decrpt[i] = deCh;
        i++;
    }

    printf("%s", decrpt);

}