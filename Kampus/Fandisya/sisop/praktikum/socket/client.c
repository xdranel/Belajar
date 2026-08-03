#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <arpa/inet.h>

#define PORT 8080
#define BUFFER_SIZE 1024

void send_command(const char *command) {
    int sock;
    struct sockaddr_in server_addr;
    char buffer[BUFFER_SIZE] = {0};

    // Membuat socket
    sock = socket(AF_INET, SOCK_STREAM, 0);
    if (sock == -1) {
        perror("Socket failed");
        exit(EXIT_FAILURE);
    }

    // Mengisi informasi alamat server
    server_addr.sin_family = AF_INET;
    server_addr.sin_port = htons(PORT);
    server_addr.sin_addr.s_addr = INADDR_ANY;

    // Terhubung ke server
    if (connect(sock, (struct sockaddr *)&server_addr, sizeof(server_addr)) == -1) {
        perror("Connection failed");
        close(sock);
        exit(EXIT_FAILURE);
    }

    // Mengirim perintah ke server
    send(sock, command, strlen(command), 0);

    // Menerima respon dari server
    int len = recv(sock, buffer, BUFFER_SIZE - 1, 0);
    if (len > 0) {
        buffer[len] = '\0';
        printf("Server response: %s\n", buffer);
    }

    close(sock);
}

int main() {
    char command[BUFFER_SIZE];
    char option[10];
    int number;

    printf("Enter command (GET or POST <number>): ");
    fgets(command, BUFFER_SIZE, stdin);
    command[strcspn(command, "\n")] = 0;  // Menghapus newline di akhir

    // Parsing input command
    sscanf(command, "%s %d", option, &number);

    if (strcmp(option, "POST") == 0) {
        snprintf(command, BUFFER_SIZE, "POST %d", number);
    } else if (strcmp(option, "GET") == 0) {
        strcpy(command, "GET");
    } else {
        printf("Invalid command.\n");
        return 1;
    }

    send_command(command);

    return 0;
}
