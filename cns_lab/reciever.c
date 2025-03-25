#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/ipc.h>
#include <sys/msg.h>

#define MSG_SIZE 256
#define OUTPUT_FILE "output.txt"
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

    // Connect to the message queue
    msgid = msgget(key, 0666 | IPC_CREAT);
    if (msgid == -1) {
        perror("msgget");
        exit(EXIT_FAILURE);
    }

    // Open the file to write
    file = fopen(OUTPUT_FILE, "w");
    if (file == NULL) {
        perror("fopen");
        exit(EXIT_FAILURE);
    }

    // Receive file content
    while (1) {
        if (msgrcv(msgid, &message, sizeof(message.msg_text), 1, 0) == -1) {
            perror("msgrcv");
            exit(EXIT_FAILURE);
        }

        // Check for termination message
        if (strcmp(message.msg_text, "EOF") == 0) {
            break;
        }

        fprintf(file, "%s", message.msg_text);
    }

    printf("File received successfully.\n");
    fclose(file);

    // Remove the message queue
    msgctl(msgid, IPC_RMID, NULL);

    return 0;
}
