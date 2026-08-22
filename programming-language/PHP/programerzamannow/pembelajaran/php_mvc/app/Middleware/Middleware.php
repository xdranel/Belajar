<?php

namespace Grp\Mvc\Middleware;

interface Middleware
{
    function before(): void;
}