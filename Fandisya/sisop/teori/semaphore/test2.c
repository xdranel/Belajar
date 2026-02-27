#include <stdio.h>
#include <pthread.h>
#include <semaphore.h>
#include <unistd.h>

sem_t mutex;
int shared_resource = 0;

void* process(void* arg) {
    int id = *(int*)arg;
    
    // Critical Section dimulai
    sem_wait(&mutex);
    
    printf("Proses %d memasuki critical section\n", id);
    // Simulasi pengerjaan
    shared_resource++;
    sleep(1);
    printf("Proses %d: shared_resource = %d\n", id, shared_resource);
    printf("Proses %d keluar dari critical section\n", id);
    
    sem_post(&mutex);
    // Critical Section berakhir
    
    return NULL;
}

void demonstrasi_semaphore(int initial_value) {
    pthread_t threads[3];
    int thread_ids[3] = {1, 2, 3};
    
    // Inisialisasi semaphore dengan nilai yang berbeda
    sem_init(&mutex, 0, initial_value);
    
    printf("\nDemonstrasi dengan nilai semaphore awal = %d\n", initial_value);
    
    // Membuat 3 thread
    for(int i = 0; i < 3; i++) {
        pthread_create(&threads[i], NULL, process, &thread_ids[i]);
    }
    
    // Menunggu semua thread selesai
    for(int i = 0; i < 3; i++) {
        pthread_join(threads[i], NULL);
    }
    
    sem_destroy(&mutex);
}

int main() {
    printf("Case 1: Semaphore diinisialisasi dengan 0\n");
    printf("(Program akan hang/deadlock)\n");
    demonstrasi_semaphore(0);
    
    printf("\nCase 2: Semaphore diinisialisasi dengan 2\n");
    printf("(Multiple proses bisa masuk critical section)\n");
    demonstrasi_semaphore(2);
    
    printf("\nCase 3: Semaphore diinisialisasi dengan 1 (Correct)\n");
    printf("(Mutual exclusion terjamin)\n");
    demonstrasi_semaphore(1);
    
    return 0;
}
