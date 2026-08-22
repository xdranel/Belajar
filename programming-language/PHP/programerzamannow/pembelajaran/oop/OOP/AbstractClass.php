<?php

require_once "data/Location.php";

use Data\{Location, City, Province, Country};

 // $location = new Location(); => Error Cant Instantiate Abstract Class

 $city = new City();
 $province = new Province();
 $country = new Country();