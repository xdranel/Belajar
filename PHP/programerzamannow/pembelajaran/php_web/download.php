<?php

if (isset($_GET['key']) && $_GET['key'] == 'rahasia') {
    header('Content-Disposition: attachment; filename="profile.jpg"');
    header('Content-type: image/jpeg');
    readfile(__DIR__ . '/uploads/profile.jpg');
    exit();
} else {
    echo "Invalid Key";
}
