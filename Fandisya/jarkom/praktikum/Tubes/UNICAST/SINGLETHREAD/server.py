import socket
import os


def start_server():
    received_folder = "file_diterima"
    copied_folder = "file_dicopy"

    server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    server_socket.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)

    host = "localhost"
    port = 12345
    server_socket.bind((host, port))

    server_socket.listen(1)
    print(f"Server {host}:{port}")

    if not os.path.exists(received_folder):
        os.makedirs(received_folder)
    if not os.path.exists(copied_folder):
        os.makedirs(copied_folder)

    print("Menunggu Koneksi..")
    client_socket, address = server_socket.accept()
    print(f"Terkoneksi ke Client : {address}")

    try:
        while True:
            data_type_bytes = client_socket.recv(1024)
            if not data_type_bytes:
                break

            data_type = data_type_bytes.decode().strip()
            if data_type != "QUIT":
                print(f"\nMenerima {data_type}")
            elif data_type == "QUIT":
                print("")

            if data_type == "TEXT":
                client_socket.send("READY".encode())

                message = client_socket.recv(4096).decode()
                print(f"Teks: {message}")
                client_socket.send("Teks Diterima".encode())

            elif data_type == "FILE":
                client_socket.send("READY".encode())

                filename = client_socket.recv(1024).decode().strip()
                print(f"File {filename}")
                client_socket.send("READY".encode())

                file_size = int(client_socket.recv(1024).decode().strip())
                client_socket.send("READY".encode())

                file_path = os.path.join(received_folder, filename)
                with open(file_path, "wb") as f:
                    received = 0
                    while received < file_size:
                        data = client_socket.recv(min(4096, file_size - received))
                        if not data:
                            break
                        f.write(data)
                        received += len(data)

                print(f"File Disimpan di: {file_path}")

                copied_file_path = os.path.join(copied_folder, filename)
                with open(file_path, "rb") as src:
                    with open(copied_file_path, "wb") as dst:
                        dst.write(src.read())
                print(f"File Dicopy ke: {copied_file_path}")

                if filename.lower().endswith(".txt"):
                    try:
                        with open(file_path, "r", encoding="utf-8") as f:
                            content = f.read()
                        print(f"Isi .TXT: {content}")
                    except Exception as e:
                        print(f"Tidak bisa membaca file.txt: {e}")

                client_socket.send("File Diterima".encode())

    except Exception as e:
        print(f"Error: {e}")
    finally:
        client_socket.close()
        server_socket.close()
        print("Koneksi Terputus - Server Berhenti")


if __name__ == "__main__":
    start_server()
