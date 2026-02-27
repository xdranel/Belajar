<?php

class Student
{
    public string $id;
    public string $name;
    public int $grade;
    private string $address;

    public function setAddress(string $address): void
    {
        $this->address = $address;
    }

    public function __clone(): void
    {
        unset($this->address);
    }
}