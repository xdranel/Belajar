<?php

use Repository\commentRepositoryImpl;
use Model\Comment;

require_once __DIR__ . '/GetConnection.php';
require_once __DIR__ . '/simple_database/Model/Comment.php';
require_once __DIR__ . '/simple_database/Repository/CommentRepository.php';

$connection = getConnection();
$repository = new commentRepositoryImpl($connection);

//Membuat comment baru dan melihat id
$comment = new Comment(email: "Repo@test.com", comment: "Hmmm");
$newComment = $repository->insert($comment);

var_dump($newComment);

// Mencari komen berdasarkan id
//$comment = $repository->findById(24);
//var_dump($comment);

//$comments = $repository->findAll();
//var_dump($comments);

$connection = null;