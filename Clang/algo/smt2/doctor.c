#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct speciality {
  int id;
  char *name;
} Speciality;

typedef struct doctor {
  int id;
  char *name;
  int spesialityId; // Terkoneksi dengan struct speciality
  int experience;
  int fee;
} Doctor;

// konsep Circular doubly linked list
typedef struct practice {
  int id;
  Doctor doctor;
  int schedule[7][3]; // 7 hari, setiap 1 hari ada 3 slot
  struct practice *next;
  struct practice *prev;
} Practice;

Practice *head = NULL;
Practice *tail = NULL;

Practice *createNode(Doctor doctor, int spesialityId, int *scheduleCount) {
  Practice *newNode = (Practice *)malloc(sizeof(Practice));
  newNode->id = *scheduleCount + 1;
  newNode->doctor = doctor;
  newNode->next = NULL;
  newNode->prev = NULL;

  for (int i = 0; i < 7; i++) {
    for (int j = 0; j < 3; j++) {
      newNode->schedule[i][j] = 0;
    }
  }

  *scheduleCount += 1;

  return newNode;
}

void addDoctor(Doctor *doctors, int *doctorCount, Speciality *specialities,
               int specialityCount) {
  // Apakah sudah ada spesialisasi? Kalau belum, output belum ada dan keluar
  // fungsi
  if (specialityCount == 0) {
    printf("Spesialisasi belum ada\n");
    return;
  }

  int id, spesialityId, experience, fee;
  char name[100];

  printf("Masukkan nama dokter: ");
  scanf(" %[^\n]", name);
  printf("Masukkan spesialisasi dokter: ");
  scanf("%d", &spesialityId);
  printf("Masukkan pengalaman dokter: ");
  scanf("%d", &experience);
  printf("Masukkan biaya dokter: ");
  scanf("%d", &fee);

  // Validasi apakah spesialisasi valid atau tidak (tidak melebihi jumlah
  // spesialiasi di variabel 'specialityCount')
  int valid = 0;
  for (int i = 0; i < specialityCount; i++) {
    if (specialities[i].id == spesialityId) {
      valid = 1;
      break;
    }
  }

  // Kalau valid, tambahkan dokter ke dalam array 'doctors'
  if (valid) {
    id = *doctorCount + 1;
    // Allocate memory for new doctor's name
    char *newName = malloc(strlen(name) + 1);
    strcpy(newName, name);
    Doctor newDoctor = {id, newName, spesialityId, experience, fee};
    doctors[*doctorCount] = newDoctor;
    *doctorCount += 1;
    printf("Dokter berhasil ditambahkan\n");
  } else {
    printf("Spesialisasi tidak valid\n");
  }
}

void editDoctor(Doctor *doctors, int doctorCount, Speciality *specialities,
                int specialityCount) {
  int id, spesialityId, experience, fee;
  char *name;
  printf("Masukkan ID dokter: ");
  scanf("%d", &id);

  // Validasi ID Dokter ditemukan atau tidak melalui looping
  int found = 0;
  for (int i = 0; i < doctorCount; i++) {
    if (doctors[i].id == id) {
      found = 1;
      printf("Masukkan nama dokter: ");
      scanf(" %[^\n]", name);
      printf("Masukkan spesialisasi dokter: ");
      scanf("%d", &spesialityId);
      printf("Masukkan pengalaman dokter: ");
      scanf("%d", &experience);
      printf("Masukkan biaya dokter: ");
      scanf("%d", &fee);

      // Validasi spesialisasi
      int valid = 0;
      for (int i = 0; i < specialityCount; i++) {
        if (specialities[i].id == spesialityId) {
          valid = 1;
          break;
        }
      }

      if (valid) {
        doctors[i].id = id;
        doctors[i].name = name;
        doctors[i].spesialityId = spesialityId;
        doctors[i].experience = experience;
        doctors[i].fee = fee;
        printf("Dokter berhasil diubah\n");
      } else {
        printf("Spesialisasi tidak valid\n");
      }
      break;
    }
  }

  if (!found) {
    printf("Dokter tidak ditemukan\n");
  }
}

void showDoctor(Doctor *doctors, int doctorCount, Speciality *specialities,
                int specialityCount) {
  for (int i = 0; i < doctorCount; i++) {
    printf("ID: %d\n", doctors[i].id);
    printf("Nama: %s\n", doctors[i].name);
    if (doctors[i].spesialityId != 0) {
      printf("Spesialisasi: %s\n",
             specialities[doctors[i].spesialityId - 1].name);
    } else {
      printf("Spesialisasi: N/A (Silahkan tambah spesialisasi pada dokter "
             "ini!)\n");
    }
    printf("Pengalaman: %d tahun\n", doctors[i].experience);
    printf("Biaya: Rp%d\n", doctors[i].fee);
    printf("\n");
  }
}

void showDoctorBySpeciality(Doctor *doctors, int doctorCount,
                            Speciality *specialities, int specialityCount) {
  int spesialityId;
  printf("Masukkan ID spesialisasi: ");
  scanf("%d", &spesialityId);

  for (int i = 0; i < doctorCount; i++) {
    if (doctors[i].spesialityId == spesialityId) {
      printf("ID: %d\n", doctors[i].id);
      printf("Nama: %s\n", doctors[i].name);
      printf("Spesialisasi: %s\n",
             specialities[doctors[i].spesialityId - 1].name);
      printf("Pengalaman: %d tahun\n", doctors[i].experience);
      printf("Biaya: Rp%d\n", doctors[i].fee);
      printf("\n");
    }
  }
}

void deleteDoctor(Doctor *doctors, int *doctorCount) {
  int id;
  printf("Masukkan ID dokter: ");
  scanf("%d", &id);

  // Cek, apakah ID dokter valid atau tidak
  int found = 0;
  for (int i = 0; i < *doctorCount; i++) {
    if (doctors[i].id == id) {
      found = 1; // berarti dokter udah ketemu (valid)
      for (int j = i; j < *doctorCount - 1; j++) {
        doctors[j] = doctors[j + 1];
      }
      *doctorCount -= 1;
      printf("Dokter berhasil dihapus\n");
      break;
    }
  }

  if (!found) {
    printf("Dokter tidak ditemukan\n");
  }
}

void addSpeciality(Speciality *specialities, int *specialityCount) {
  int id;
  char name[100];
  printf("Masukkan nama spesialisasi: ");
  scanf(" %[^\n]", name);

  id = *specialityCount + 1;
  // Allocate memory for new speciality's name
  char *newName = malloc(strlen(name) + 1);
  strcpy(newName, name);
  Speciality newSpeciality = {id, newName};
  specialities[*specialityCount] = newSpeciality;
  *specialityCount += 1;
  printf("Spesialisasi berhasil ditambahkan\n");
}

void showSpeciality(Speciality *specialities, int specialityCount) {
  for (int i = 0; i < specialityCount; i++) {
    printf("ID: %d\n", specialities[i].id);
    printf("Nama: %s\n", specialities[i].name);
    printf("\n");
  }
}

void editSpeciality(Speciality *specialities, int specialityCount) {
  int id;
  char name[100];
  printf("Masukkan ID spesialisasi: ");
  scanf("%d", &id);

  int found = 0;
  for (int i = 0; i < specialityCount; i++) {
    if (specialities[i].id == id) {
      found = 1;
      printf("Masukkan nama spesialisasi: ");
      scanf(" %[^\n]", name);

      // Allocate memory for new speciality's name
      char *newName = malloc(strlen(name) + 1);
      strcpy(newName, name);
      specialities[i].id = id;
      specialities[i].name = newName;
      printf("Spesialisasi berhasil diubah\n");
      break;
    }
  }

  if (!found) {
    printf("Spesialisasi tidak ditemukan\n");
  }
}

void deleteSpeciality(Speciality *specialities, int *specialityCount,
                      Doctor *doctors, int doctorCount) {
  int id;
  printf("Masukkan ID spesialisasi: ");
  scanf("%d", &id);

  int found = 0;
  for (int i = 0; i < *specialityCount; i++) {
    if (specialities[i].id == id) {
      found = 1;
      for (int j = i; j < *specialityCount - 1; j++) {
        specialities[j] = specialities[j + 1];
      }

      // Set spesialisasi dokter yang spesialisasinya dihapus menjadi N / A
      for (int j = 0; j < doctorCount; j++) {
        if (doctors[j].spesialityId == id) {
          doctors[j].spesialityId = 0;
        }
      }

      *specialityCount -= 1;
      printf("Spesialisasi berhasil dihapus\n");
      break;
    }
  }

  if (!found) {
    printf("Spesialisasi tidak ditemukan\n");
  }
}

void insertFirstSchedule(Doctor *doctors, int doctorCount, int *scheduleCount) {
  int id, day, slot;
  printf("Masukkan ID dokter: ");
  scanf("%d", &id);

  // Cari dokter dulu
  int found = 0;
  for (int i = 0; i < doctorCount; i++) {
    if (doctors[i].id == id) {
      found = 1; // Dokter ditemukan
      printf("Masukkan hari (1-7): ");
      scanf("%d", &day);
      printf("Masukkan slot (1-3): ");
      scanf("%d", &slot);

      if (day >= 1 && day <= 7 && slot >= 1 && slot <= 3) {
        Practice *newNode = createNode(doctors[i], doctors[i].spesialityId,
                                       (int *)scheduleCount);
        newNode->schedule[day - 1][slot - 1] = 1;

        if (!head) // Apakah linked list kosong?
        {
          head = tail = newNode;
        } else {
          newNode->next = head;
          head->prev = newNode;
          head = newNode;
        }
        printf("Jadwal berhasil ditambahkan\n");
      } else {
        printf("Hari atau slot tidak valid\n");
      }
      break;
    }
  }

  if (!found) {
    printf("Dokter tidak ditemukan\n");
  }
}

void insertLastSchedule(Doctor *doctors, int doctorCount, int *scheduleCount) {
  int id, day, slot;
  printf("Masukkan ID dokter: ");
  scanf("%d", &id);

  // Cari dokter dulu
  int found = 0;
  for (int i = 0; i < doctorCount; i++) {
    if (doctors[i].id == id) {
      found = 1; // Dokter ditemukan
      printf("Masukkan hari (1-7): ");
      scanf("%d", &day);
      printf("Masukkan slot (1-3): ");
      scanf("%d", &slot);

      if (day >= 1 && day <= 7 && slot >= 1 && slot <= 3) {
        Practice *newNode = createNode(doctors[i], doctors[i].spesialityId,
                                       (int *)scheduleCount);
        newNode->schedule[day - 1][slot - 1] = 1;

        if (!head) // Apakah linked list kosong?
        {
          head = tail = newNode;
        } else {
          tail->next = newNode;
          newNode->prev = tail;
          tail = newNode;
        }
        printf("Jadwal berhasil ditambahkan\n");
      } else {
        printf("Hari atau slot tidak valid\n");
      }
      break;
    }
  }
}

void insertAfterSchedule(Doctor *doctors, int doctorCount, int *scheduleCount) {
  int id, day, slot;
  printf("Masukkan ID dokter: ");
  scanf("%d", &id);

  // Cari dokter dulu
  int found = 0;
  for (int i = 0; i < doctorCount; i++) {
    if (doctors[i].id == id) {
      found = 1; // Dokter ditemukan
      printf("Masukkan hari (1-7): ");
      scanf("%d", &day);
      printf("Masukkan slot (1-3): ");
      scanf("%d", &slot);

      if (day >= 1 && day <= 7 && slot >= 1 && slot <= 3) {
        Practice *newNode = createNode(doctors[i], doctors[i].spesialityId,
                                       (int *)scheduleCount);
        newNode->schedule[day - 1][slot - 1] = 1;

        if (!head) // Apakah linked list kosong?
        {
          head = tail = newNode;
        } else {
          int afterId;
          printf("Masukkan ID setelah: ");
          scanf("%d", &afterId);

          // cek, apakah ID di tail atau tidak
          if (tail->doctor.id == afterId) {
            tail->next = newNode;
            newNode->prev = tail;
            tail = newNode;
          } else {
            Practice *curr = head;
            while (curr) {
              if (curr->doctor.id == afterId) {
                newNode->next = curr->next;
                newNode->prev = curr;
                curr->next->prev = newNode;
                curr->next = newNode;
                break;
              }
              curr = curr->next;
            }
          }
        }
        printf("Jadwal berhasil ditambahkan\n");
      } else {
        printf("Hari atau slot tidak valid\n");
      }
      break;
    }
  }

  if (!found) {
    printf("Dokter tidak ditemukan\n");
  }
}

void showSchedule(Practice *head, Speciality *specialities) {
  Practice *curr = head;
  while (curr) {
    printf("ID Jadwal: %d | Dokter: %s | Spesialisasi: %s\n", curr->id,
           curr->doctor.name, specialities[curr->doctor.spesialityId - 1].name);
    for (int i = 0; i < 7; i++) {
      for (int j = 0; j < 3; j++) {
        if (curr->schedule[i][j] == 1) {
          char *day;
          switch (i) {
          case 0:
            day = "Senin";
            break;
          case 1:
            day = "Selasa";
            break;
          case 2:
            day = "Rabu";
            break;
          case 3:
            day = "Kamis";
            break;
          case 4:
            day = "Jumat";
            break;
          case 5:
            day = "Sabtu";
            break;
          case 6:
            day = "Minggu";
            break;
          default:
            break;
          }
          printf("Hari: %s | Slot: %d\n", day, j + 1);
        }
      }
    }
    printf("\n");
    curr = curr->next;
  }
}

void deleteFirstSchedule(Doctor *doctors, int doctorCount) {
  if (!head) {
    printf("List is empty\n");
    return;
  }

  Practice *temp = head;
  head = head->next;
  if (head) {
    head->prev = NULL;
  }
  free(temp);
}

void deleteLastSchedule(Doctor *doctors, int doctorCount) {
  if (!head) {
    printf("List is empty\n");
    return;
  }

  Practice *temp = tail;
  tail = tail->prev;
  if (tail) {
    tail->next = NULL;
  }
  free(temp);
}

void deleteAfterSchedule(Doctor *doctors, int doctorCount) {
  if (!head) {
    printf("List is empty\n");
    return;
  }

  int afterId;
  printf("Masukkan ID setelah: ");
  scanf("%d", &afterId);

  Practice *curr = head;
  while (curr) {
    if (curr->doctor.id == afterId) {
      Practice *temp = curr->next;
      curr->next = temp->next;
      if (temp->next) {
        temp->next->prev = curr;
      }
      free(temp);
      break;
    }
    curr = curr->next;
  }
}

int main() {
  Doctor doctors[100];
  int doctorCount = 0;
  Speciality specialities[100];
  int specialityCount = 0;
  int scheduleCount = 0;

  int choice;
  do {
    printf("Menu\n");
    printf("========================= Dokter =========================\n");
    printf("1. Tambah Dokter\n");
    printf("2. Edit Dokter\n");
    printf("3. Tampilkan Dokter\n");
    printf("4. Tampilkan Dokter Berdasarkan Spesialisasi\n");
    printf("5. Hapus Dokter\n");
    printf(
        "========================= Spesialisasi =========================\n");
    printf("6. Tambah Spesialisasi\n");
    printf("7. Tampilkan Spesialisasi\n");
    printf("8. Edit Spesialisasi\n");
    printf("9. Hapus Spesialisasi\n");
    printf("========================= Jadwal =========================\n");
    printf("10. Tambah Jadwal (Insert First)\n");
    printf("11. Tambah Jadwal (Insert Last)\n");
    printf("12. Tambah Jadwal (Insert After)\n");
    printf("13. Tampilkan Jadwal\n");
    printf("14. Hapus Jadwal (Delete First)\n");
    printf("15. Hapus Jadwal (Delete Last)\n");
    printf("16. Hapus Jadwal (Delete After)\n");
    printf("========================= Utility =========================\n");
    printf("17. Bersihkan CLI\n");
    printf("0. Keluar\n");
    printf("Pilih: ");
    scanf("%d", &choice);

    switch (choice) {
    case 1:
      addDoctor(doctors, &doctorCount, specialities, specialityCount);
      break;
    case 2:
      editDoctor(doctors, doctorCount, specialities, specialityCount);
      break;
    case 3:
      showDoctor(doctors, doctorCount, specialities, specialityCount);
      break;
    case 4:
      showDoctorBySpeciality(doctors, doctorCount, specialities,
                             specialityCount);
      break;
    case 5:
      deleteDoctor(doctors, &doctorCount);
      break;
    case 6:
      addSpeciality(specialities, &specialityCount);
      break;
    case 7:
      showSpeciality(specialities, specialityCount);
      break;
    case 8:
      editSpeciality(specialities, specialityCount);
      break;
    case 9:
      deleteSpeciality(specialities, &specialityCount, doctors, doctorCount);
      break;
    case 10:
      insertFirstSchedule(doctors, doctorCount, &scheduleCount);
      break;
    case 11:
      insertLastSchedule(doctors, doctorCount, &scheduleCount);
      break;
    case 12:
      insertAfterSchedule(doctors, doctorCount, &scheduleCount);
      break;
    case 13:
      showSchedule(head, specialities);
      break;
    case 14:
      deleteFirstSchedule(doctors, doctorCount);
      break;
    case 15:
      deleteLastSchedule(doctors, doctorCount);
      break;
    case 16:
      deleteAfterSchedule(doctors, doctorCount);
      break;
    case 17:
      printf("\e[1;1H\e[2J");
      break;
    default:
      break;
    }
  } while (choice != 0);

  return 0;
}
