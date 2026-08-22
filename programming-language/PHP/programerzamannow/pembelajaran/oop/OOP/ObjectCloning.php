<?php

require_once "data/Student.php";

$student1 = new Student();
$student1->id = "A230011140601";
$student1->name = "Ramona";
$student1->grade = 4;
$student1->setAddress("Jl. Merpati No. 1");

var_dump($student1);

$student2 = clone $student1;
var_dump($student2);

// Manual Cloning
// $student2 = new Student();
// $student2->id = $student1->id;
// $student2->name = $student1->name;
// $student2->grade = $student1->grade;

// order __clone function
// $student1 cloned by => $student2 proceded by => __clone() into $student2