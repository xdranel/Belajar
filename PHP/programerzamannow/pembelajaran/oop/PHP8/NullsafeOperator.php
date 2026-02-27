<?php

class Address
{
    public ?string $country;
}

class User
{
    public ?Address $address;
}

// PHP 7.4
// How to use Nullsafe Operator
function getCountry(?User $user): ?string
{
    if ($user != null) {
        if ($user->address != null) {
            return $user->address->country;
        }
    }
    return null;

    // return $user->address->country;
}

echo getCountry(null);

// PHP 8
// How to use Nullsafe Operator
function getCountry2(?User $user): ?string
{
    return $user?->address?->country;
}
echo getCountry2(null);
