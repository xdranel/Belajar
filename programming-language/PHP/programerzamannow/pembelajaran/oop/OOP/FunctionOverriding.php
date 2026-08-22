<?php

require_once "data/Manager.php";

$manager = new Manager("Ramona");
$manager->sayHello("Joko");

$vp = new VicePresident("Budi");
$vp->sayHello("Joko");