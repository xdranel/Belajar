#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <arpa/inet.h>

#define PORT 8080
#define MAX_NUMBERS 100
#define BUFFER_SIZE 1024

int numbers[MAX_NUMBERS];
int count = 0;

void handle_client(int client_socket) {
    char buffer[BUFFER_SIZE];
    int num;
    
    // Menerima data dari client
    recv(client_socket, buffer, BUFFER_SIZE, 0);

    // Memeriksa perintah dari client
    if (strncmp(buffer, "POST", 4) == 0) {
        // Perintah POST
        if (sscanf(buffer + 5, "%d", &num) == 1 && count < MAX_NUMBERS) {
            numbers[count++] = num;
            send(client_socket, "Number added\n", 13, 0);
        } else {
            send(client_socket, "Failed to add number\n", 21, 0);
        }
    } else if (strncmp(buffer, "GET", 3) == 0) {
        // Perintah GET
        char response[BUFFER_SIZE] = "";
        char temp[12];
        
        for (int i = 0; i < count; i++) {
            sprintf(temp, "%d ", numbers[i]);
            strcat(response, temp);
        }
        if (count == 0) {
            strcpy(response, "No numbers stored\n");
        }
        send(client_socket, response, strlen(response), 0);
    } else {
        // Perintah tidak dikenal
        send(client_socket, "Bad request\n", 12, 0);
    }

    close(client_socket);
}

int main() {
    int server_socket, client_socket;
    struct sockaddr_in server_addr, client_addr;
    socklen_t addr_len = sizeof(client_addr);

    // Membuat socket
    server_socket = socket(AF_INET, SOCK_STREAM, 0);
    if (server_socket == -1) {
        perror("Socket failed");
        exit(EXIT_FAILURE);
    }

    // Mengisi informasi alamat server
    server_addr.sin_family = AF_INET;
    server_addr.sin_addr.s_addr = INADDR_ANY;
    server_addr.sin_port = htons(PORT);

    // Mengikat socket ke alamat
    if (bind(server_socket, (struct sockaddr *)&server_addr, sizeof(server_addr)) == -1) {
        perror("Bind failed");
        close(server_socket);
        exit(EXIT_FAILURE);
    }

    // Memulai mendengarkan koneksi
    if (listen(server_socket, 5) == -1) {
        perror("Listen failed");
        close(server_socket);
        exit(EXIT_FAILURE);
    }
    printf("Server listening on port %d\n", PORT);

    // Menerima koneksi client
    while ((client_socket = accept(server_socket, (struct sockaddr *)&client_addr, &addr_len)) != -1) {
        handle_client(client_socket);
    }

    close(server_socket);
    return 0;
}
