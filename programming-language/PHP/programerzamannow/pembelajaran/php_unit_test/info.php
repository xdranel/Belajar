<?php
/**

=================
List Pembelajaran

# PHP Unit
# Making Unit Test
# Assertion
# Annotation
# Test Dependency
# Data Provider
# Exception
# Output
# Fixture
# Sharing Fixtures
# Incomplete
# Skip
# Stub
# Mock Object

=================
> Cek Folder confusion(PHP Unit Test.pdf)
> Dibaca Cik

==========
# PHP Unit
> Dibaca Cik pdfnya

- untuk memulai seperti biasa gunakan composer init
- setelah itu memberikan require-dev phpunit/phpunit pada composer.json
- lalu lakukan composer install dan setelah selesai meng-install lakukan composer dump-autoload


==================
# Making Unit Test
> Dibaca Cik pdfnya
> Cek File Counter.php, CounterTest.php


===========
# Assertion
> Dibaca Cik pdfnya
> Cek File Counter.php, CounterTest.php

- Assertion adalah mengecek apakah sebuah kondisi sudah terpenuhi, jika kondisi tidak terpenuhi maka unit test
di anggap gagal


=>? NOTES : Annotation, Dependedncy, Data Provider

- tidak menggunakan cara ver.9 namun menggunakan cara ver.10
- pada ver.9 masih menggunakan DocBlock sedangkan ver.10 sudah menggunakan cara attributes yang diperkenalkan pada
PHP 8
=> https://docs.phpunit.de/en/10.5/annotations.html
=> https://docs.phpunit.de/en/12.0/writing-tests-for-phpunit.html#writing-tests-for-phpunit-test-dependencies
=> https://docs.phpunit.de/en/12.0/writing-tests-for-phpunit.html#data-providers

============
# Annotation
> Dibaca cik pdfnya

- menggunakan attribute #[Test] php unit akan langsung mengenalinya
- jangan lupa menggunakan use PHPUnit\Framework\TestCase;
agar bisa menggunakan #[Test]


# Test Dependency
> Dibaca cik pdfnya
> Cek File Counter.php, CounterTest.php

Caranya:
- seperti attribute #[Depends('functionName')]
- jangan lupa menggunakan use PHPUnit\Framework\Attributes\Depends;
agar bisa menggunakan #[Depends('functionName')]

=> Notes, baca pdfnya cik ada peringatan

===============
# Data Provider
> Dibaca cik pdfnya[[5, 5], 10],
> Cek File Math.php, MathTest.php

Caranya:
- Seperti attribute #[DataProvider('functionName')]

- Ada cara lebih gampang tanpa membuat function untuk DataProvider dengan menggunakan #[TestWith($value)]
- digunanakan pada kasus-kasus sederhana



===========
# Exception
> Dibaca cik pdfnya
> Cek File Person.php, PersonTest.php

- disaat membuat unit test juga diperlukan skenario gagal
https://docs.phpunit.de/en/12.0/writing-tests-for-phpunit.html#expecting-exceptions

========
# Output
> Dibaca cik pdfnya
> Cek File Person.php, PersonTest.php

- disaat membuat unit test juga diperlukan untuk mendeteksi output contoh : echo
https://docs.phpunit.de/en/12.0/writing-tests-for-phpunit.html#testing-output


=========
# Fixture
> Dibaca cik pdfnya
> Cek File Person.php, PersonTest.php

- membuat kode awal yang berulang-ulang sebelum unit test memakan waktu atau membuat kode akhir yang berulang-ulang
setelah unit test Hal ini dinamakan Fixture
- menggunakan setUp() dam tearDown()
- hati hati setup akan dipanggil setiap sebuah test dijalankan
https://docs.phpunit.de/en/12.0/fixtures.html#


# Sharing Fixtures
> Dibaca cik pdfnya
> Read the picture FXT00*.png
> Cek File Counter.php, CounterStaticTest.php
https://docs.phpunit.de/en/12.0/fixtures.html#sharing-fixture


=================
# Incomplete Test
> Dibaca cik pdfnya
> Cek File CounterTest.php
https://docs.phpunit.de/en/12.0/writing-tests-for-phpunit.html#incomplete-tests


======
# Skip
> Dibaca cik pdfnya
> Cek File CounterTest.php
https://docs.phpunit.de/en/12.0/writing-tests-for-phpunit.html#skipping-tests


# Stub
> Dibaca cik pdfnya
> Cek File Product.php, ProductRepository.php, ProductService.php, ProductServiceTest.php
https://docs.phpunit.de/en/12.0/test-doubles.html

- Selalu mengembalikan default valuenya jika default valuenya nullable akan mengembalikan null, jika default valuenya
array akan mengembalikan array kosong, jika void mengembalikan kosong dst
- dan kita ingin bahwa mengembalikan nya tidak default atau dengan kondisi tertentu misal query id nya atau yg lainnya

- caranya Konfigurasi Stub menggunakan function method(name) yang mana akan mengembalikan InvocationStubber


=============
# Mock Object
> Dibaca cik pdfnya
> Cek File Product.php, ProductRepository.php, ProductService.php, ProductServiceMockTest.php
https://docs.phpunit.de/en/12.0/test-doubles.html#mock-objects

- Mock object improvemnet dari stub apa yang bisa dilakukan stub bisa dilakukan mock object
- dengan mock object bisa melakukan verifikasi berapa banyak sebuah method dipanggil





































































































































 */


