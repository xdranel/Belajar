class Ticket:
    def __init__(self, ticket_number, customer_name):
        self.ticket_number = ticket_number
        self.customer_name = customer_name
    
    def Print(self):
        return f"{self.ticket_number}: {self.customer_name}"


class Queue:
    def __init__(self):
        self.items = []
    
    def enqueue(self, item):
        self.items.append(item)
    
    def dequeue(self):
        if self.is_empty():
            return "Kosong"
        return self.items.pop(0)
    
    def is_empty(self):
        return len(self.items) == 0
    
    def size(self):
        return len(self.items)

def main():
    queue = Queue()
    ticket_counter = 1
    
    while True:
        print("\n=== Sistem Antrian Tiket ===")
        print("1. Tambah pelanggan")
        print("2. Proses antrian")
        print("3. Tampilkan antrian")
        print("4. Keluar")
        choice = input("Pilih opsi (1-4): ").strip()
        
        if choice == '1':
            name = input("Masukkan nama pelanggan: ").strip()
            if name:
                ticket = Ticket(ticket_counter, name)
                queue.enqueue(ticket)
                ticket_counter += 1
                print(f"Pelanggan {name} ditambahkan dengan nomor tiket {ticket.ticket_number}.")
            else:
                print("Nama tidak boleh kosong!")
        
        elif choice == '2':
                processed_ticket = queue.dequeue()
                print(f"Memproses: {processed_ticket}")
                print(f"Jumlah pelanggan tersisa: {queue.size()}")
        
        elif choice == '3':
            if queue.is_empty():
                print("Antrian kosong.")
            else:
                print("\nDaftar pelanggan dalam antrian:")
                for i, ticket in enumerate(queue.items, 1):
                    print(f"{i}. {ticket}")
                print(f"Total pelanggan dalam antrian: {queue.size()}")
        
        elif choice == '4':
            print("Terima kasih! Program dihentikan.")
            break
        
        else:
            print("Opsi tidak valid! Pilih 1-4.")

if __name__ == "__main__":
    main()
