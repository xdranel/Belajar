#include <stdio.h>
#include <pthread.h>
#include <semaphore.h>
#include <unistd.h>

// Deklarasi semaphore
sem_t helm;
sem_t dompet;

// Fungsi untuk simulasi delay
void delay(int seconds) {
    sleep(seconds);
}

void menujuBunderan() {
    printf("Sedang menuju ke bundaran kampus...\n");
    delay(2);
}

void menujuBandung() {
    printf("Berangkat menuju Bandung...\n");
    delay(2);
}

// Prosedur untuk Mahasiswa A
void* mahasiswaA(void* arg) {
    printf("Mahasiswa A: Sadar helm tertinggal di kosan B\n");
    
    // Menuju bundaran untuk bertemu
    printf("Mahasiswa A: Membawa dompet milik B\n");
    menujuBunderan();
    
    // Memberikan dompet ke B dan menunggu helm
    printf("Mahasiswa A: Sampai di bundaran\n");
    sem_post(&dompet);  // Memberikan dompet ke B
    printf("Mahasiswa A: Memberikan dompet ke B\n");
    
    sem_wait(&helm);    // Menunggu helm dari B
    printf("Mahasiswa A: Menerima helm\n");
    
    // Berangkat ke Bandung
    printf("Mahasiswa A: Siap berangkat ke Bandung\n");
    menujuBandung();
    printf("Mahasiswa A: Dalam perjalanan ke Bandung\n");
    
    return NULL;
}

// Prosedur untuk Mahasiswa B
void* mahasiswaB(void* arg) {
    printf("Mahasiswa B: Sadar dompet tertinggal di kosan A\n");
    
    // Menuju bundaran untuk bertemu
    printf("Mahasiswa B: Membawa helm milik A\n");
    menujuBunderan();
    
    // Memberikan helm ke A dan menunggu dompet
    printf("Mahasiswa B: Sampai di bundaran\n");
    sem_post(&helm);    // Memberikan helm ke A
    printf("Mahasiswa B: Memberikan helm ke A\n");
    
    sem_wait(&dompet);  // Menunggu dompet dari A
    printf("Mahasiswa B: Menerima dompet\n");
    
    // Berangkat ke Bandung
    printf("Mahasiswa B: Siap berangkat ke Bandung\n");
    menujuBandung();
    printf("Mahasiswa B: Dalam perjalanan ke Bandung\n");
    
    return NULL;
}

int main() {
    // Inisialisasi semaphore
    sem_init(&helm, 0, 0);
    sem_init(&dompet, 0, 0);
    
    // Deklarasi thread
    pthread_t threadA, threadB;
    
    // Membuat thread
    printf("Program dimulai\n");
    pthread_create(&threadA, NULL, mahasiswaA, NULL);
    pthread_create(&threadB, NULL, mahasiswaB, NULL);
    
    // Menunggu thread selesai
    pthread_join(threadA, NULL);
    pthread_join(threadB, NULL);
    
    // Destroy semaphore
    sem_destroy(&helm);
    sem_destroy(&dompet);
    
    printf("Program selesai\n");
    return 0;
}
