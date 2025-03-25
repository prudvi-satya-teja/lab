#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/ipc.h>
#include <sys/msg.h>
 
#define MSG_SIZE 256
#define FILE_PATH "input.txt"
#define QUEUE_KEY 1234

struct msg_buffer {
    long msg_type;
    char msg_text[MSG_SIZE];
};

int main() {
    key_t key = QUEUE_KEY;
    int msgid;
    struct msg_buffer message;
    FILE *file;

    // Create message queue
    msgid = msgget(key, 0666 | IPC_CREAT);
    if (msgid == -1) {
        perror("msgget");
        exit(EXIT_FAILURE);
    }

    // Open the file to read
    file = fopen(FILE_PATH, "r");
    if (file == NULL) {
        perror("fopen");
        exit(EXIT_FAILURE);
    }

    // Read and send the file content
    message.msg_type = 1;
    while (fgets(message.msg_text, MSG_SIZE, file) != NULL) {
        if (msgsnd(msgid, &message, sizeof(message.msg_text), 0) == -1) {
            perror("msgsnd");
            exit(EXIT_FAILURE);
        }
    }

    // Send termination message
    strcpy(message.msg_text, "EOF");
    msgsnd(msgid, &message, sizeof(message.msg_text), 0);

    printf("File sent successfully.\n");
    fclose(file);

    return 0;
}
