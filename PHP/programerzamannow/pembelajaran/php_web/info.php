<?php

/**
=================
LIST PEMBELAJARAN

# Connecting
# Integration With HTML
# Global Variable Server
# Query Parameter
# XSS (Cross Site Scripting)
# Form Post
# Header
# Redirect
# Response
# Session
# Cookie
# Session & Cookie
# Upload File
# Download File

=================



============
# Connecting
- Menggunakan PHP Development Server
php -S host:port


=======================
# Integration With HTML
> Cek file html.php

Cara input di dalam html
1. <?php ?>
2. <?= ?>

Contoh
1. <?php echo $foo ?>
2. <?= $foo ?>


========================
# Global Variable Server
$_SERVER
> Cek File server.php

- Berisikan Informasi mengenai SERVER, HTTP, REMOTE, METHOD DLL


=================
# Query Parameter
> Cek File get.php, get-multiple.php, get-array.php

- Ketika mengakses web menggunakan URL di dalam URL terdapat bagian yang bernama Query Parameter
- Query Parameter merupakan informasi tambahan yang kita kirimkan dari Client ke Server secara dinamis
- Di PHP semua Query Parameter secara otomatis disimpan di dalam global variable bernama $_GET

=> $_GET
- $_GET adalah global variable berupa array yang berisikan key sesuai dengan nama query paramater dan value
sesuai dengan value parameter
- Jika mengirim query parameter name=budi, akan ada key name di dalam $_GET yang bernilai budi

- Perlu di ingat, query parameter merupakan data yang dikirim oleh Client. jadi pastikan sebelum mengunakannya harus
mengecek apakah datanya ada atau tidak

Contoh Di URL :
** Single Query Parameter
localhost:8080/get.php?name=Budi => Budi

localhost:8080/get.php?name=Budi Gunawan or localhost:8080/get.php?name=Budi%20Gunawan => Budi Gunawan

** Multiple Query Parameter
http://localhost:8080/get-multiple.php?first_name=Budi&last_name=Hermawan => Budi Hermawan

http://localhost:8080/get-multiple.php?first_name=Budi&last_name=Hermawan&first_name=Eko => Eko Hermawan
first_name = Budi di replace first_name = Eko

** Array Query Parameter
http://localhost:8080/get-array.php?numbers[]=1&numbers[]=6 => Total = 7


============================
# XSS (Cross Site Scripting)
> Cek File get.php

- celah keamanan yang biasa di eksploitasi dengan cara mengirim script pada parameter

Contoh :
http://localhost:8080/get.php?name=Budi%3C/h1%3E%3Cscript%3Ealert(%22Halo%20Kamu%22)%3C/script%3E%3Ch1%3EBudi%20Lagi%3C/h1%3E

- pada url tersebut akan berjalan script js alert()
check view page source

- untuk mengatasi nya bisa mengunakan function htmlspecialchars(value)
check view page source setelah mengunakan      ^^^^^^^^^^^^^^^^


===========
# Form Post
> Cek File form.php, post.php

- Secara default method di form adalah GET, sehingga ketika di submit data akan dikirim dalam bentuk Query Parameter
yang bisa ditangkap menggunakan $_GET di PHP

- Tetapi jika mengunakan method POST, maka secara otomatis request dari Client ke Server akan berubah HTTP POST.
hal ini menyebabkan data form yang dikirim akan dikirim melalui body bukan Query Parameter, dan untuk menerimanya
menggunakan $_POST

- $_POST global variable yang berisikan data array yang dikirim dari Client Ke Server dalam bentuk form post

NB : Selalu Ingat ketika mengunakan $_GET atau &_POST pastikan value dari key nya ada


========
# Header
> Cek File header.php

- Semua header yang dikirim Client, secara otomatis akan dimasukkan ke global variable $_SERVER
- namun key untuk header akan secara otomatis di konversi menjadi UPPERCASE, dan jika terdapat spasi atau -
otomatis akan diganti menjadi _
- Selain itu untuk membedakan request header dan lainnya, khusus request header, akan ditambah PREFIX HTTP_

Misal :
key = Content-Type => HTTP_CONTENT_TYPE

=> Menambahkan Header Response

- menggunakan function header('key: value')


==========
# Redirect
> Cek File redirect.php, redirect-external.php

- Dalam pembuatan halaman web, melakukan redirect dari satu halaman ke halaman lainnya adalah hal biasa
Misalkan setelah sukse login di redirect ke halaman web nya ke halaman member atau admin
Atau redirect ke domain berbeda, misal login dengan Google setelah selesai di redirect ke halaman web kita

=> HTTP Redirect
- untuk melakukan redirect hanya butuh menggunakan response header location yang berisi url redirect nya
Misal :
Location: /admin.php => redirect ke halaman admin di domain yang sama
Location: https://www.google.com => redirect ke halaman google


==========
# Response
> Cek File get-validation.php

- Secara default di PHP response code adalah 200 OK
- Dalam spesifikasi HTTP, respponse dari server biasanya memiliki code, atau dikenal HTTP Response Code
Cek => https://developer.mozilla.org/en-US/docs/Web/HTTP/Status
- Dalam pembuatan website jarang sekali melakukan perubahan response code, namun jika nanti kita ingin membuat API
menggunakan PHP, response coded sangat penting untuk digunakan


=========
# Session
> Cek File login.php, member.php, logout.php

- mekanisme yang digunakan untuk mengingat interaksi sebelumnya dari Client
- Di Spesifikasi HTTP tidak dikenal yang namanya Session, karena HTTP di desain STATELESS(tidak menyimpa state, data
atau infromasi apapun)
Contoh:
- ketika proses login setelah login sukses kita akan membuat Session dan Request selanjutnya dari Client akan membawa
informasi tersebut


=> $_SESSION
- merupakan global variable array yang berisi data session
- Setelah Session di start kita bisa menggunakan $_SESSION untuk menyimpan data Session atau mengambil data di Session
- Jika Session belum di start perubahan yang dilakukan di $_SESSION atau pengambilan data di $_SESSION tidak akan
berpengaruh apa-apa

NB : pengunaan PHP SESSION tidak direkomendasikan


========
# Cookie
> Cek File cookie.php, show-cookie.php

- Cookie adalah data dalam bentuk key-value yang dikirim oleh Server pada HTTP Response untuk disimpan di Client
- Ketika web browser menerima cookie, secara otomatis request selanjutnya cookie tersebut akan dikirim di setiap
HTTP Request yang dilakukan server

- menggunakan function setcookie()
- Cookie merupakan bagian dari HTTP Header, jadi pastikan cookie dibuat sebelum content di render di PHP
- untuk membaca cookie bisa menggunakan global variable $_COOKIE

NB : Hati - Hati Menggunakan Cookie
- jangan terlalu banyak data di Cookie, karena semua data di cookie akan selalu dikirim di tiap HTTP Request
semakin banyak semakin lambat karena data yang harus dikirim banyak
- Cookie juga bisa diedit secara manual oleh user
- pastikan ketika menggunakan Cookie bukan hal yang sensistif


==================
# Session & Cookie

=============
# Upload File
> Cek Folder /uploads
> Cek File upload.php,

- Di PHP untuk file yang di upload dari server bisa ditangkap dengan global variable $_FILES
- Setiap ada file yang di upload, seecara otomatis $_FILES akan berisi informasi seputar file yang di upload

- Saat di upload ke server, file akan secara otomatis di simpan di temporary folder di server, dari situ file bisa
dipindahkan ke lokasi yang lain

NB : Perlu di ingat
- disaat mengecek di folder temporarynya file yang did upload tidak akan karena setelah di request file dihapus maka
dari itu sebelum selesai requst baiknya file juga dipindah ke lokasi lain jika file tersebut di inginkan


===============
# Download File

>

- Saat membuat web terkadang butuh mengembalikan content berupa file yang butuh di download oleh client
- kita bisa langsung mengakses file tersebut melalui URL, namun kita juga bisa mengunakan PHP untuk membuat content
dalam bentuk file dan memkasa Client untuk mendownload

































































































 */