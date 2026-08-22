import socket
import os
import threading


def handle_client(client_socket, address, client_id):
    """Handle individual client connection"""
    received_folder = f"Folder_Diterima_Client_{client_id}"
    copied_folder = f"Folder_Dicopy_Client_{client_id}"

    print(f"[CLIENT {client_id}] Terkoneksi dari: {address}")

    if not os.path.exists(received_folder):
        os.makedirs(received_folder)
        print(f"[CLIENT {client_id}] Membuat Folder: {received_folder}")
    if not os.path.exists(copied_folder):
        os.makedirs(copied_folder)
        print(f"[CLIENT {client_id}] Membuat Folder: {copied_folder}")

    try:
        while True:
            data_type_bytes = client_socket.recv(1024)
            if not data_type_bytes:
                break

            data_type = data_type_bytes.decode().strip()
            print(f"[CLIENT {client_id}] Menerima {data_type}")

            if data_type == "TEXT":
                # Send acknowledgment first
                client_socket.send("READY".encode())

                # Receive text message
                message = client_socket.recv(4096).decode()
                print(f"[CLIENT {client_id}] Text received: {message}")
                client_socket.send("Text received".encode())

            elif data_type == "FILE":
                # Send acknowledgment first
                client_socket.send("READY".encode())

                # Receive file
                # Get filename
                filename = client_socket.recv(1024).decode().strip()
                print(f"[CLIENT {client_id}] Receiving file: {filename}")
                client_socket.send("READY".encode())

                # Get file size
                file_size = int(client_socket.recv(1024).decode().strip())
                print(f"[CLIENT {client_id}] File size: {file_size} bytes")
                client_socket.send("READY".encode())

                # Receive file data
                file_path = os.path.join(received_folder, filename)
                with open(file_path, "wb") as f:
                    received = 0
                    while received < file_size:
                        data = client_socket.recv(min(4096, file_size - received))
                        if not data:
                            break
                        f.write(data)
                        received += len(data)

                print(f"[CLIENT {client_id}] File saved as: {file_path}")

                # Copy file to copied_files folder
                copied_file_path = os.path.join(copied_folder, filename)
                with open(file_path, "rb") as src:
                    with open(copied_file_path, "wb") as dst:
                        dst.write(src.read())
                print(f"[CLIENT {client_id}] File copied to: {copied_file_path}")

                # If it's a txt file, show the content
                if filename.lower().endswith(".txt"):
                    try:
                        with open(file_path, "r", encoding="utf-8") as f:
                            content = f.read()
                        print(f"[CLIENT {client_id}] Text file content:\n{'-' * 40}")
                        print(content)
                        print(f"{'-' * 40}")
                    except Exception as e:
                        print(
                            f"[CLIENT {client_id}] Could not read text file content: {e}"
                        )

                # Show file type info
                file_ext = (
                    filename.lower().split(".")[-1] if "." in filename else "unknown"
                )
                if file_ext in ["jpg", "jpeg", "png"]:
                    print(
                        f"[CLIENT {client_id}] Image file ({file_ext}) received and copied"
                    )
                elif file_ext == "mp3":
                    print(f"[CLIENT {client_id}] Audio file (mp3) received and copied")
                elif file_ext == "mp4":
                    print(f"[CLIENT {client_id}] Video file (mp4) received and copied")
                elif file_ext in ["pdf", "docx"]:
                    print(
                        f"[CLIENT {client_id}] Document file ({file_ext}) received and copied"
                    )

                client_socket.send("File received".encode())

            elif data_type == "QUIT":
                print(f"[CLIENT {client_id}] Client requested to quit")
                client_socket.send("Goodbye".encode())
                break

    except Exception as e:
        print(f"[CLIENT {client_id}] Error: {e}")
    finally:
        client_socket.close()
        print(f"[CLIENT {client_id}] Disconnected")


def start_multithread_server():
    # Create socket
    server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    server_socket.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)

    # Bind socket
    host = "localhost"
    port = 12345
    server_socket.bind((host, port))

    # Listen for connections
    server_socket.listen(5)  # Allow up to 5 connections in queue
    print(f"Multithreaded Server listening on {host}:{port}")
    print("Server can handle multiple clients simultaneously")
    print("Press Ctrl+C to stop the server")

    client_counter = 0

    try:
        while True:
            # Accept client connections
            client_socket, address = server_socket.accept()
            client_counter += 1

            # Create a new thread for each client
            client_thread = threading.Thread(
                target=handle_client, args=(client_socket, address, client_counter)
            )
            client_thread.daemon = True  # Thread will close when main program closes
            client_thread.start()

            print(
                f"Active clients: {threading.active_count() - 1}"
            )  # -1 for main thread

    except KeyboardInterrupt:
        print("\nServer shutting down...")
    except Exception as e:
        print(f"Server error: {e}")
    finally:
        server_socket.close()
        print("Server closed")


if __name__ == "__main__":
    start_multithread_server()
