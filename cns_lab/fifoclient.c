#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h>
#include <unistd.h>
#include <string.h>

int main() {
    int wrfd, rdfd;
    char msg[50] = "hello world", response[50];

    // Open pipes
    wrfd = open("np1", O_WRONLY);
    rdfd = open("np2", O_RDONLY);

    // Write to server
    write(wrfd, msg, strlen(msg));
    printf("Client: Sent message - %s\n", msg);

    // Read response from server
    read(rdfd, response, sizeof(response));
    printf("Client: Received response - %s\n", response);

    return 0;
}
