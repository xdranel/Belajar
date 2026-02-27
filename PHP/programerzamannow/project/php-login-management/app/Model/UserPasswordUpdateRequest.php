<?php

namespace Grp\Belajar\Model;

class UserPasswordUpdateRequest
{
    public ?string $id = null;
    public ?string $oldPassword = null;
    public ?string $newPassword = null;

}