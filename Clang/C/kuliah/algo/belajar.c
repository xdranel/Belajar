// DOKUMENTASI PENTING C YANG KU KERJAKAN

/* ============== INDEX ===============
CONTOH SOAL 1 : BARIS, KOLOM, PENGULANGAN PROGRAM
CONTOH SOAL 2 : IF ELSE STATEMENT
CONTOH SOAL 3 : KONVERSI METER KE FEET
CONTOH SOAL 4 : ARRAY, STRING, CHARACTER.
CONTOH SOAL 5 : LIBRARY <string.h> <ctype.h>
CONTOH SOAL 6 : FUNCTION
CONTOH SOAL 7 : PASS ARRAY TO FUNCTION
CONTOH SOAL 8 : POINTER
CONTOH SOAL 9 : PASS BY REFERENCE
CONTOH SOAL 10 : POINTER VS ARRAY NOTATION
CONTOH SOAL 11 : DYNAMIC MALLOC
CONTOH SOAL 12 : TYPEDEF AND STRUCT
CONTOH SOAL 13 :
*/

// JANGAN LUPA BAHWA SETIAP KODE DI DALAM SUATU BLOK ENTAH ITU DI DALAM () {}
// ATAUPUN FUNCTION, KODE TERSEBUT HANYA MILIK BLOK ITU. ADA CARANYA UNTUK
// MEMANGIL KODE TERSEBUT JIKA KODE TERSEBUT DI DALAM BLOK.

// CONTOH SOAL 1 FOR, WHILE
/*
#include <stdio.h>

int main(){

//contoh 1.1
    int baris, kolom;
    char pengulangan_program;
    int nilai[5];
    do{
        printf("berapa baris : ");
        scanf(" %d", &baris);
        printf("berapa kolom : ");
        scanf(" %d", &kolom);
        printf("\n");

        for (int b = 0; b < 5; b++){
            printf("input nilai ke %d : ", b);
            scanf(" %d", &nilai[b]);
        }

        printf("\n");
        for (int n = 0; n < 5; n++){
            printf("nilai ke %d : %d\n", n, nilai[n]);
        }

        printf("\n");
        for (int i = 0; i < baris; i++){
            for (int j = 1; j < kolom; j++){
                printf("%d", nilai[i]);
            }
            printf("\n");
        }
        printf("mengulangi program (y/n) : ");
        scanf(" %c", &pengulangan_program);
    } while (pengulangan_program == 'y');

//contoh 1.2 (mencari max)
    int max = -1, angka = 0;

    while(angka != -1){
        printf("masukan angka : ");
        scanf("%d", &angka);
        if(angka > max) max = angka;
    }

    printf("max : %d\n", max);
    return 0;
}
*/
// END OF CONTOH SOAL 1

// CONTOH SOAL 2 IF ELSE STATEMENT
/*
#include <stdio.h>

int main(){

    int umur, tes_tulis, avg_raport;

    scanf(" %d", &umur);
    if (umur > 20)
    {
        printf("mengikuti tes tulis\n");
        scanf(" %d", &tes_tulis);
        if (tes_tulis > 75)
        {
            printf("mengumpulkan raport\n");
            scanf("%d", &avg_raport);
            if (avg_raport >= 80)
            {
                printf("maba berhak memilih seluruh prodi\n");
            }
            else if (avg_raport >= 70 && avg_raport < 80)
            {
                printf("maba diterima di prodi elektro\n");
            }
            else if (avg_raport >= 65 && avg_raport < 70)
            {
                printf("maba diterima di prodi digbis\n");
            }
            else
            {
                printf("maba di tolak\n");
            }
        }
        else if (tes_tulis <= 75)
        {
            printf("maba di tolak\n");
        }
    }
    else if (umur < 21)
    {
        printf("mengumpulkan raport\n");
        scanf("%d", &avg_raport);
        if (avg_raport >= 80)
        {
            printf("maba berhak memilih seluruh prodi\n");
        }
        else if (avg_raport >= 70 && avg_raport < 80)
        {
            printf("maba diterima di prodi elektro\n");
        }
        else if (avg_raport >= 65 && avg_raport < 70)
        {
            printf("maba diterima di prodi digbis\n");
        }
        else
        {
            printf("maba di tolak\n");
        }
    }
    return 0;
}
*/
// END OF SONTOH SOAL 2

// CONTOH SOAL 3 Konversi Meter KE Feet
/*
#include <stdio.h>

int main(){

    double initial = 0, step = 0, stop = 0;

    do{
        printf("Initial (m): ");
        scanf("%lf", &initial);
        if(initial < 0) printf("must be >= 0\n");
    }while(initial < 0);

    do{
        printf("step (m): ");
        scanf("%lf", &step);
        if(step <= 0) printf("must be > 0\n");
    }while(step <= 0);

    do{
        printf("stop (m): ");
        scanf("%lf", &stop);
        if(stop < 0) printf("must be >= 0\n");
    }while(stop < 0);
    printf("\n");

    printf("Meters    Feet\n");
    printf("********************\n");
    for(double conv = initial; conv <= stop; conv += step){
        printf("%-10.2lf%-10.2lf\n", conv, conv * 3.28084);
    }
    printf("\n");

    return 0;
}
*/
// END OF CONTOH SOAL 3

// CONTOH SOAL 4 ARRAY, STRING, CHARACTER.
/*
#include <stdio.h>

int main(){
//contoh 4.1
    char x[] = "gendhi ramona";
    for(int i = 0; i < 14; i++){
        printf("array index [%d] : %c\n", i, x[i]);
    }
    printf("\n%s\n", x);

//contoh 4.2
    char nama[20];
    fgets(nama, sizeof(nama), stdin);
    for(int j = 0; j < sizeof(nama); j++){
        printf("array index [%d] : %c\n", j, nama[j]);
    }
    printf("\n%s\n", nama);

//contoh 4.3 berapa banyak 'character'
    char x[20];
    printf("Enter : ");
    scanf("%s", x);

    int i = 0;
    while(x[i] != '\0'){
        if(x[i] == '0')
            printf("found 0\n");
            i++;
    }
    printf("x : %s\n", x);

    return 0;
}
*/
// END OF CONTOH SOAL 4

// CONTOH SOAL 5 lIBRARY <string.h> <ctype.h>
/*
#include <ctype.h>
#include <stdio.h>
#include <string.h>

int main(){

=============<string.h>=============

//contoh 5.1 strlen (panjang string) (program berapa panjang array string char x
dan berapa banyak huruf tertentu dalam string tersebut)

    char x[] = "lewat sini";
    int panjang = strlen(x);
    int hurufW = 0;

    for(int i = 0; i < panjang; i++){
        if(x[i] == 'w') hurufW++;
    }

    printf("panjang huruf : %d\n", panjang);
    printf("banyaknya huruf w : %d\n", hurufW);

//contoh 5.2 strcat (mengabungkan)
    char x1[40] = "kalimat pertama. ";
    char x2[] = "kalimat kedua";

    strcat(x1,x2);

    printf("%s", x1);

//contoh 5.3 strcmp (membandingkan) (program memeriksa apakah kedua string sama)
    char x1[] = "kalimat pertama";
    char x2[] = "kalimat Pertama";

    if(strcmp(x1,x2) == 0){
        printf("x1 dan x2 sama\n");
    }
    else{
        printf("x1 dan x2 tidak sama\n");
    }




=============<ctype.h>==============

//contoh 5.1  isupper/islower (program mengecek huruf besar atau kecil dan
meng-output) char x[] = "aKu SaYaNg KaMu"; int panjang = strlen(x);

    for(int i = 0; i < panjang; i++){
        printf("%c ", x[i]);
        if(isupper(x[i])){
            printf("Huruf Besar\n");
        }
        else if(islower(x[i])){
            printf("Huruf Kecil\n");
        }
        else printf("\n");
    }

//contoh 5.2 isupper/islower (program yang sama dengan 5.1 tetapi sekarang
membalik huruf kecil menjadi besar dengan outputnya dan vice versa) char x[] =
"aKu SaYaNg KaMu"; int panjang = strlen(x);

    printf("kalimat awal : %s\n\n", x);

    for(int i = 0; i < panjang; i++){
        printf("%c ", x[i]);
        if(isupper(x[i])){
            x[i] = tolower(x[i]);
            printf("Huruf Besar Menjadi Huruf Kecil %c\n", x[i]);
        }
        else if(islower(x[i])){
            x[i] = toupper(x[i]);
            printf("Huruf Kecil Menjadi Huruf Besar %c\n", x[i]);
        }
        else printf("\n");
    }
    printf("\n");
    printf("kalimat sekarang : %s\n", x);
    return 0;
}
*/
// END OF CONTOH SOAL 5

// CONTOH SOAL 6 FUNCTION
/*
#include <stdio.h>

// contoh 6.1
int find_max(int x, int y);

int main(){
    int a, b;
    scanf("%d", &a);
    scanf("%d", &b);

    int max = find_max(a,b);

    printf("max : %d\n", max);
    return 0;
}

int find_max(int x, int y){
    if (x > y) return x;
    else return y;

// contoh 6.2
double power(double x, int n);
double mult(double x, double y);

int main(){
    double a,b;
    scanf("%lf", &a);
    scanf("%lf", &b);

    printf("%.2lf\n", power(a,b));

    return 0;
}

double power(double x, int n){
    double result = x;
    for(int i = 1; i< n; i++){
        result = mult(result, x);
    }
    return result;
}

double mult(double x, double y){
    return x * y;
}

// contoh 6.3
void print_two(float a,float b);

int main(){

    float x,y;

    scanf("%f", &x);
    scanf("%f", &y);
    print_two(x, y);

    return 0;
}
void print_two(float a,float b){
    printf("%.2f\n%.2f\n", a,b);
}

// contoh 6.4
*/
// END OF CONTOH SOAL 6

// CONTOH SOAL 7 PASS ARRAY TO FUNCTION
/*
#include <stdio.h>

// contoh 7.1
void add_one(int array[], int length);

int main(){
    int hai[6];

    for(int i = 0; i < 6; i++){
        printf("nilai array[%d] : ", i+1);
        scanf("%d", &hai[i]);
    }

    add_one(hai, 6);
    for(int i = 0; i < 6; i++){
        printf("nilai array hai +1[%d] = %d\n", i+1, hai[i]);
    }
    return 0;
}

void add_one(int array[], int length){
    for(int i = 0; i < length; i++) array[i] += 1;
}
*/
// END OF CONTOH SOAL 7

// CONTOH SOAL 8 POINTER
/*
#include <stdio.h>

// contoh 8.1 (karena bukan array/pointer atau variabel biasa untuk
// menginput adress ke dalam "p = ?" harus mengunakan "&" "&a","&b")
int main(){

    int *p;
    int a = 5, b = 10;

    p = &b;
    printf(" p: %p\n", p);
    printf("&b: %p\n", &b);

    print("\n");

    printf("&a: %p\n", &a);
    p = &a;
    printf(" p: %p\n", p);

    print("\n");

    printf(" a: %d\n", a);
    *p = *p + 1;
    printf(" a: %d\n", a);

    return 0;
}

// contoh 8.2
int main() {
  int a;
  int *pa;
  int **ppa;
  int ***pppa;

  a = 20;
  pa = &a;
  ppa = &pa;
  pppa = &ppa;

  printf("%p\n", &a);
  printf("%p\n", pa);
  printf("%p\n", *ppa);
  printf("%p\n", **pppa);
  return 0;
}

*/
// END OF CONTOH SOAL 8

// CONTOH SOAL 9 PASS BY REFERENCE(POINTER) TO FUNCTION
/*
#include <stdio.h>

// contoh 9.1
void add_one(int *a, int *b, int *c);

int main(){

    int x1=1, x2=2, x3=3;
    add_one(&x1,&x2,&x3);

    printf("x1: %d\n", x1);
    printf("x2: %d\n", x2);
    printf("x3: %d\n", x3);

    return 0;
}

void add_one(int *a, int *b, int *c){
    *a = *a + 1;
    *b = *b + 1;
    *c = *c + 1;
}

// contoh 9.2
void per(int *a);
int per2(int a);

int main() {
  int a = 10;
  per(&a);
  printf("%d\n", a);
  printf("%d\n", per2(a));
  return 0;
}

void per(int *a) {
  *a = 42069;
  ;
}

int per2(int a) {
  int result = a;
  return result = 69420;
}

contoh 9.3


*/
// END OF CONTOH SOAL 9

// CONTOH SOAL 10 POINTER VS ARRAY NOTATION
/*
#include <stdio.h>

============= ARRAY NOTATION =============

// contoh 10.1 (harus ada *p untuk p = a) dan (di bagian "p = a")
// tidak usah mengunakan "&a" karena "a[]" merupakan array karena "a[]"
// sudah bagian dari memori adress

int main(){

    int a[] = {1,2,3};
    int *p;
    p = a;

    printf("p: %p\n", p); // keduanya memiliki memori adress
    printf("a: %p\n", a); // yang sama

    printf("p[1] = %d\n", p[1]); // keduanya memiliki nilai
    printf("a[1] = %d\n", a[1]); // yang sama
    return 0;
}

// contoh 10.2 (sama seperti contoh 7.1) tetapi kali ini
// menggunakan array notation "int *p", "p = hai"
// dibagian scanf tetap mengunakan "&", walaupun pointer "&p[i]"
// dan dibagian function add_one mengunakan * , karena di bagian "add_one(p,6)"
// p merupakan bagian dari pointer jadi parameternya ditambahkan *
// maskutnya yaitu p mengarah ke array hai yang merupakan memori address jadi
// dibagian parameter fungsi memakai *

void add_one(int *array, int length);

int main(){

    int hai[6];
    int *p;
    p = hai;

    for(int i = 0; i < 6; i++){
        printf("nilai array[%d] : ", i+1);
        scanf("%d", &p[i]);
    }

    add_one(p, 6);
    for(int i = 0; i < 6; i++){
        printf("nilai array hai +1[%d] = %d\n", i+1, p[i]);
    }
    return 0;
}

void add_one(int *array, int length){
    for(int i = 0; i < length; i++) array[i] += 1;
}

// contoh 10.3


============= POINTER NOTATION =============
// permasalahan de-reference selalu ingat apa yang
// di de-reference kan apakah variabel biasa atau array
// jika variabel biasa ketika terjadi penambahan maka
// nilai variabel tersebut yang akan di tambahkan
// jika itu array ketika terjadi penambahan maka
// akan terjadi perpindahan nilai yang ada di dalam array
// dan bukan nilanya yang akan di tambahkan

// contoh 10.1
int main(){

    int a[] = {1,2,3};
    int *p;
    p = a;

    printf("*(p+?) = %d\n", *p );
    printf("*(p+?) = %d\n", *(p) );// sama akan mengakses nilai pertama(0) dari
array tersebut dengan mengunakan de-reference pointer " *p "

    printf("*(p+?) = %d\n", *(p + 1));
    printf("*(p+?) = %d\n", *(p + 2));// sama seperti sebelumnya mengakses nilai
array pertama(0) tetapi terjadi penambahan 1/2 di dalam memori adress yang
artinya memori ke 1/2 dan nilai ke 1/2

    printf("*(a+?) = %d\n", *(a + 1));// sama saja
    printf("*(a+?) = %d\n", *(a + 2));// de-reference pointer berguna untuk
mengambil nilai dari memori adress tersebut return 0;
}

//contoh 10.2



// contoh 10.3
int main(){

    int *z,*x,*c;
    int x1=1, x2=2, x3=3;

    z = &x1;
    x = &x2;
    c = &x3;

    printf("x1: %d\n", *z);
    printf("x2: %d\n", *x);
    printf("x3: %d\n", *c);

    return 0;
}


*/
// END OF CONTOH SOAL 10

// CONTOH SOAL 11 DYNAMIC MALLOC
/*
#include <stdio.h>
#include <stdlib.h>

// contoh 11.1 (program penambahan size array)
int main(){
    int *angka;
    int sizeAngka = 2;
    angka = malloc(sizeof(int)*sizeAngka);
    int input = 0, i = 0;

    do{
        if(i == sizeAngka){
            sizeAngka +=2;
            angka = realloc(angka, sizeof(int)*sizeAngka);
            printf("ukuran memori %d\n", sizeAngka);
        }
        printf("Masukan angka (-1 to q): ");
        scanf(" %d", &input);
        if(input != -1){
            angka[i] = input;
            i++;
        }
    }while(input != -1);

    int angkaElement = i;
    int total = 0;
    for(int j = 0; j < angkaElement; j++){
        total += angka[j];
    }

    printf("\e[H\e[2J\e[3J");
    printf("rata rata : %d\n", total/angkaElement);

    free(angka);
    return 0;
}

// contoh 11.2 (malloc tidak membersihkan memori adressnya dan nilainya)
int main() {

  int *a;
  a = malloc(sizeof(int) * 3);
  0 [a] = 1;
  1 [a] = 3;
  2 [a] = 5;

  for (int i = 0; i < 3; i++) {
    printf("%d ", a[i]);
  }
  printf("\n");
  free(a);
  return 0;
}

// contoh 11.3 (calloc menset nilai menjadi 0 tetapi memakan waktu lebih lama
dari malloc)
int main() {

  int *a;
  a = calloc(3, sizeof(int));
  0 [a] = 1;
  1 [a] = 3;
  2 [a] = 5;

  for (int i = 0; i < 3; i++) {
    printf("%d ", a[i]);
  }
  printf("\n");
  free(a);
  return 0;
}

*/
// END OF CONTOH SOAL 11

// CONTOH 12 TYPEDEF AND STRUCT
/*

// contoh 12.1 (berbagai cara mendefinisikan struct)
#include <stdio.h>

struct data {
  int umur;
  int nilai;
};

int main() {

  struct data data;
  data.umur = 10;
  data.nilai = 70;
  printf("%d ", data.umur);
  printf("%d", data.nilai);

  printf("\n");

  struct data data1 = {20, 75};
  printf("(%d,%d)", data1.umur, data1.nilai);
  printf("\n");

  struct data data2 = {.umur = 30, .nilai = 85};
  printf("%d ", data2.umur);
  printf("%d", data2.nilai);

  printf("\n");
  return 0;
}

// contoh 12.2

*/
// END OF CONTOH SOAL 12
