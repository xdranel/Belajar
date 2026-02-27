<?php

require_once "data/Programmer.php";

// ** Polymorphism **
$company = new Company();

$company->programmer = new Programmer("Budi");
var_dump($company);

$company->programmer = new BackendProgrammer("Budi");
var_dump($company);

$company->programmer = new FrontendProgrammer("Budi");
var_dump($company);

// as you can see it went from Programmer to BackendProgrammer to FrontendProgrammer
// changing the form of the object


// ** Function Argument Polymorphism **
sayHelloProgrammer(new Programmer("Budi"));
sayHelloProgrammer(new BackendProgrammer("Budi"));
sayHelloProgrammer(new FrontendProgrammer("Budi"));
// ** Type Check and Cast **