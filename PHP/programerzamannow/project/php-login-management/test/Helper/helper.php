<?php
namespace Grp\Belajar\App {
    function header(string $value)
    {
        echo $value;
    }
}

namespace Grp\Belajar\Service {
    function setCookie(string $name, string $value)
    {
        echo "$name : $value";
    }
}