#include <windows.h>
#include <iostream>

LPVOID fiberUtama = NULL;
LPVOID fiberA = NULL;
LPVOID fiberB = NULL;

bool selesaiA = false; // Penanda apakah ULT A sudah selesai

// ULT A: Berjalan 5 Kali
VOID CALLBACK tugasULT_A(LPVOID lpParam) {
    for (int i = 1; i <= 5; ++i) {
        std::cout << "[ULT A (5x)] Langkah ke-" << i << std::endl;
        
        // Serahkan giliran ke ULT B
        SwitchToFiber(fiberB);
    }

    // Setelah 5 perulangan selesai
    selesaiA = true;
    std::cout << ">>> [ULT A] SUDAH SELESAI <<<\n";

    // Kembalikan eksekusi ke ULT B agar B bisa menyelesaikan sisa perulangannya
    SwitchToFiber(fiberB);
}

// ULT B: Berjalan 10 Kali
VOID CALLBACK tugasULT_B(LPVOID lpParam) {
    for (int j = 1; j <= 10; ++j) {
        std::cout << "   [ULT B (10x)] Langkah ke-" << j << std::endl;

        // Jika ULT A BELUM selesai, kembalikan giliran ke A.
        // Jika ULT A SUDAH selesai, B lanjut terus sampai langkah ke-10 tanpa pindah ke A.
        if (!selesaiA) {
            SwitchToFiber(fiberA);
        }
    }

    std::cout << "   >>> [ULT B] SUDAH SELESAI <<<\n";

    // Semua selesai, kembali ke thread utama (main)
    SwitchToFiber(fiberUtama);
}

int main() {
    // 1. Inisialisasi Fiber Utama
    fiberUtama = ConvertThreadToFiber(NULL);

    // 2. Buat Fiber A (5x) dan Fiber B (10x)
    fiberA = CreateFiber(0, tugasULT_A, NULL);
    fiberB = CreateFiber(0, tugasULT_B, NULL);

    std::cout << "=== DEMO ULT: 5 KALI vs 10 KALI ===\n\n";

    // 3. Jalankan ULT A pertama kali
    SwitchToFiber(fiberA);

    std::cout << "\n=== SEMUA ULT SELESAI ===" << std::endl;

    // Bersihkan memori
    DeleteFiber(fiberA);
    DeleteFiber(fiberB);

    return 0;
}
