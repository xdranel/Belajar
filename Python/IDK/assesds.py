import os


class Node:
    def __init__(self, info):
        self.info = info
        self.next = None
        self.prev = None


class DoublyLinkedList:
    def __init__(self):
        self.first = None
        self.last = None


class TextEditor:
    def __init__(self):
        self.dll = DoublyLinkedList()
        self.cursor = None

    def createNode(self, char):
        return Node(char)

    def insertFirst(self, ptr):
        if self.dll.first is None:
            self.dll.first = ptr
            self.dll.last = ptr
            ptr.next = None
            ptr.prev = None
        else:
            ptr.next = self.dll.first
            ptr.prev = None
            self.dll.first.prev = ptr
            self.dll.first = ptr

    def insertAfter(self, prec, ptr):
        if prec is None:
            return

        ptr.next = prec.next
        ptr.prev = prec

        if prec.next is not None:
            prec.next.prev = ptr
        else:
            self.dll.last = ptr

        prec.next = ptr

    def insertBefore(self, prec, ptr):
        if prec is None:
            return

        ptr.next = prec.next
        ptr.prev = prec

        if prec.next is not None:
            prec.next.prev = ptr
        else:
            self.dll.last = ptr

        prec.next = ptr

    def deleteFirst(self):
        if self.dll.first is None:
            return None

        ptr = self.dll.first

        if self.dll.first == self.dll.last:
            self.dll.first = None
            self.dll.last = None
        else:
            self.dll.first = self.dll.first.next
            self.dll.first.prev = None
            ptr.next = None

        return ptr

    def deleteAfter(self, prec):
        if prec is None or prec.next is None:
            return None

        ptr = prec.next
        prec.next = ptr.next

        if ptr.next is not None:
            ptr.next.prev = prec
        else:
            self.dll.last = prec

        ptr.next = None
        ptr.prev = None

        return ptr

    def addCharacterAfterCursor(self, char):
        ptr = self.createNode(char)

        # Jika list kosong
        if self.dll.first is None:
            self.insertFirst(ptr)
            self.cursor = ptr
            print(
                f"Karakter '{char}' ditambahkan (list kosong). Cursor di: '{self.cursor.info}'"
            )
        # Jika cursor None, tambahkan di awal
        elif self.cursor is None:
            self.insertFirst(ptr)
            self.cursor = ptr
            print(
                f"Karakter '{char}' ditambahkan di awal. Cursor di: '{self.cursor.info}'"
            )
        # Tambahkan setelah cursor
        else:
            self.insertAfter(self.cursor, ptr)
            self.cursor = ptr
            print(
                f"Karakter '{char}' ditambahkan setelah cursor. Cursor di: '{self.cursor.info}'"
            )

    def deleteCharacterCursor(self):
        if self.cursor is None:
            print("Cursor tidak menunjuk ke karakter apapun")
            return None

        deleted_char = self.cursor.info

        # Jika cursor di posisi first
        if self.cursor == self.dll.first:
            ptr = self.deleteFirst()
            self.cursor = self.dll.first  # Pindah ke first yang baru (bisa None)
            print(f"Karakter '{deleted_char}' dihapus dari awal.")
            if self.cursor:
                print(f"Cursor sekarang di: '{self.cursor.info}'")
            else:
                print("Cursor: None (list kosong)")
        else:
            # Cari node sebelum cursor
            prev_node = self.cursor.prev
            ptr = self.deleteAfter(prev_node)
            self.cursor = prev_node  # Pindah ke posisi sebelumnya
            print(f"Karakter '{deleted_char}' dihapus.")
            print(f"Cursor sekarang di: '{self.cursor.info}'")

        return ptr

    def moveCursorLeft(self):
        if self.cursor is None:
            print("Cursor tidak bisa bergerak (posisi None)")
        elif self.cursor.prev is None:
            print("Cursor sudah di posisi paling kiri")
        else:
            self.cursor = self.cursor.prev
            print(f"Cursor bergerak ke kiri. Posisi: '{self.cursor.info}'")

    def moveCursorRight(self):
        if self.cursor is None:
            if self.dll.first is not None:
                self.cursor = self.dll.first
                print(f"Cursor bergerak ke posisi first. Posisi: '{self.cursor.info}'")
            else:
                print("List kosong, cursor tetap None")
        elif self.cursor.next is None:
            print("Cursor sudah di posisi paling kanan")
        else:
            self.cursor = self.cursor.next
            print(f"Cursor bergerak ke kanan. Posisi: '{self.cursor.info}'")

    def displayText(self):
        if self.dll.first is None:
            print("Teks: (kosong)")
            return

        text = ""
        current = self.dll.first
        cursor_pos = -1
        pos = 0

        while current is not None:
            text += current.info
            if current == self.cursor:
                cursor_pos = pos
            current = current.next
            pos += 1

        print(f"Teks: {text}")
        if cursor_pos >= 0:
            print(f"Cursor di posisi {cursor_pos}: '{self.cursor.info}'")
        else:
            print("Cursor: None")

    def setCursorToFirst(self):
        """Set cursor ke posisi first"""
        self.cursor = self.dll.first
        if self.cursor:
            print(f"Cursor dipindahkan ke awal: '{self.cursor.info}'")
        else:
            print("List kosong, cursor = None")


# Program Utama
def main():
    print("=" * 60)
    print("TEXT EDITOR SEDERHANA - DOUBLY LINKED LIST")
    print("=" * 60)

    editor = TextEditor()

    while True:
        print("\n--- Menu ---")
        print("1. Tambah karakter setelah cursor")
        print("2. Hapus karakter di cursor")
        print("3. Gerakkan cursor ke kiri")
        print("4. Gerakkan cursor ke kanan")
        print("5. Set cursor ke awal")
        print("6. Tampilkan teks")
        print("7. Bersihkan Jendela Terminal")
        print("8. Keluar")

        choice = input("\nPilih menu (1-7): ")

        if choice == "1":
            char = input("Masukkan karakter: ")
            if len(char) > 0:
                editor.addCharacterAfterCursor(char[0])
        elif choice == "2":
            editor.deleteCharacterCursor()
        elif choice == "3":
            editor.moveCursorLeft()
        elif choice == "4":
            editor.moveCursorRight()
        elif choice == "5":
            editor.setCursorToFirst()
        elif choice == "6":
            editor.displayText()
        elif choice == "7":
            os.system("clear")
        elif choice == "8":
            print("\nTerima kasih telah menggunakan Text Editor!")
            break
        else:
            print("Pilihan tidak valid!")


if __name__ == "__main__":
    main()
