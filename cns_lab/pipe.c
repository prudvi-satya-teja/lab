#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

int main() {
    int pipefd[2], n;
    char buff[100];

    if (pipe(pipefd) == -1) {
        perror("Pipe creation failed");
        exit(1);
    }

    printf("\nRead fd = %d", pipefd[0]);
    printf("\nWrite fd = %d", pipefd[1]);

    // Write data into the pipe
    write(pipefd[1], "helloworld", 10);

    // Read from the read end of the pipe
    n = read(pipefd[0], buff, sizeof(buff));
    buff[n] = '\0'; // Null terminate the string

    printf("\nSize of the data: %d", n);
    printf("\nData from pipe: %s\n", buff);

    return 0;
}
