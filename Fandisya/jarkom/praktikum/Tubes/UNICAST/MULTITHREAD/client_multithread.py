import socket
import os
import time
import random


def send_text(client_socket, message):
    """Send text message to server"""
    client_socket.send("TEXT".encode())
    # Wait for server acknowledgment
    client_socket.recv(1024)
    client_socket.send(message.encode())
    response = client_socket.recv(1024).decode()
    print(f"Server response: {response}")


def send_file(client_socket, file_path):
    """Send file to server"""
    if not os.path.exists(file_path):
        print(f"File not found: {file_path}")
        return

    # Send file type
    client_socket.send("FILE".encode())
    # Wait for server acknowledgment
    client_socket.recv(1024)

    # Send filename
    filename = os.path.basename(file_path)
    client_socket.send(filename.encode())
    client_socket.recv(1024)  # Wait for ack

    # Get file size
    file_size = os.path.getsize(file_path)
    client_socket.send(str(file_size).encode())
    client_socket.recv(1024)  # Wait for ack

    # Send file data
    with open(file_path, "rb") as f:
        while True:
            data = f.read(4096)
            if not data:
                break
            client_socket.send(data)

    response = client_socket.recv(1024).decode()
    print(f"Server response: {response}")


def main():
    # Create socket and connect to server
    client_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    host = "localhost"
    port = 12345

    # ========== CHANGE THESE VALUES ==========
    # Add client identifier for testing multiple clients
    client_name = f"Client_{random.randint(1000, 9999)}"

    # 1-5 words
    words = f"{client_name}: Hello world test"

    # Long sentence
    long_sentence = f"{client_name}: This is a very long sentence that contains multiple words and demonstrates the capability of sending longer text messages through socket programming from this specific client."

    # Paragraph
    paragraph = f"""{client_name}: This is a sample paragraph that contains multiple sentences from {client_name}. 
    It demonstrates how we can send longer text content through socket programming. 
    Socket programming allows us to establish communication between client and server applications. 
    This paragraph shows that we can successfully transmit larger amounts of text data from multiple clients simultaneously."""

    # File paths - CHANGE THESE TO YOUR ACTUAL FILE PATHS
    document_path = "sample.txt"  # Change to your document path
    image_path = "sample.jpg"  # Change to your image path
    audio_path = "sample.mp3"  # Change to your audio path
    video_path = "sample.mp4"  # Change to your video path
    # ========================================

    try:
        client_socket.connect((host, port))
        print(f"{client_name} connected to server {host}:{port}")

        # Add small delays to simulate real-world usage and avoid overwhelming the server

        # Send 1-5 words
        print(f"{client_name}: Sending 1-5 words...")
        send_text(client_socket, words)
        time.sleep(1)

        # Send long sentence
        print(f"{client_name}: Sending long sentence...")
        send_text(client_socket, long_sentence)
        time.sleep(1)

        # Send paragraph
        print(f"{client_name}: Sending paragraph...")
        send_text(client_socket, paragraph)
        time.sleep(1)

        # Send document
        print(f"{client_name}: Sending document...")
        send_file(client_socket, document_path)
        time.sleep(1)

        # Send image
        print(f"{client_name}: Sending image...")
        send_file(client_socket, image_path)
        time.sleep(1)

        # Send audio
        print(f"{client_name}: Sending audio...")
        send_file(client_socket, audio_path)
        time.sleep(1)

        # Send video
        print(f"{client_name}: Sending video...")
        send_file(client_socket, video_path)
        time.sleep(1)

        # Send quit signal
        client_socket.send("QUIT".encode())
        response = client_socket.recv(1024).decode()
        print(f"{client_name}: Server said: {response}")
        print(f"{client_name}: All data sent. Disconnecting...")

    except Exception as e:
        print(f"{client_name}: Error: {e}")
    finally:
        client_socket.close()
        print(f"{client_name}: Connection closed")


if __name__ == "__main__":
    main()
