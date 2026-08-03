import socket
import os


def send_text(client_socket, message):
    """Send text message to server"""
    client_socket.send("TEXT".encode())

    client_socket.recv(1024)
    client_socket.send(message.encode())
    response = client_socket.recv(1024).decode()
    print(f"Respon Server: {response}")


def send_file(client_socket, file_path):
    """Send file to server"""
    if not os.path.exists(file_path):
        print(f"File Tidak Ditemukan: {file_path}")
        return

    client_socket.send("FILE".encode())
    client_socket.recv(1024)

    filename = os.path.basename(file_path)
    client_socket.send(filename.encode())
    client_socket.recv(1024)

    file_size = os.path.getsize(file_path)
    client_socket.send(str(file_size).encode())
    client_socket.recv(1024)

    with open(file_path, "rb") as f:
        while True:
            data = f.read(4096)
            if not data:
                break
            client_socket.send(data)

    response = client_socket.recv(1024).decode()
    print(f"Respon Server: {response}")


def main():
    client_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    host = "localhost"
    port = 12345

    words = "For You"

    long_sentence = (
        "A Poem For The Girl That I Like and Love From Me Your Secret Admirer"
    )

    paragraph = """This is a sample paragraph that contains multiple sentences.
    It demonstrates how we can send longer text content."""

    document_path = "../../FILE/teks.txt"
    image_path = "../../FILE/oshiku.jpg"
    audio_path = "../../FILE/seasons.mp3"
    video_path = "../../FILE/shortest.mp4"

    try:
        client_socket.connect((host, port))
        print(f"Terhubung Ke Server {host}:{port}")

        print("Mengirim 1-5 Kalimat")
        send_text(client_socket, words)

        print("Mengirim Kalimat Panjang")
        send_text(client_socket, long_sentence)

        print("Mengirim Paragraf")
        send_text(client_socket, paragraph)

        print("Mengirim Dokumen")
        send_file(client_socket, document_path)

        print("Mengirim Gambar")
        send_file(client_socket, image_path)

        print("Mengirim Audio")
        send_file(client_socket, audio_path)

        print("Mengirim Video")
        send_file(client_socket, video_path)

        client_socket.send("QUIT".encode())
        print("Semua Data Terkirim")

    except Exception as e:
        print(f"Error: {e}")
    finally:
        client_socket.close()
        print("Koneksi Terputus")


if __name__ == "__main__":
    main()
