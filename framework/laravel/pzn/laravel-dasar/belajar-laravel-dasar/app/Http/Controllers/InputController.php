<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;

class InputController extends Controller
{
    public function hello(Request $request): string
    {
        $name = $request->input('name');
        return "Hello $name";
    }

    public function helloFirstName(Request $request): string
    {
        $firstName = $request->input('name.first');
        $lastName = $request->input('name.last');
        return "Hello $firstName $lastName";
    }

    public function helloInput(Request $request): string // accept all input
    {
        $input = $request->input();
        return json_encode($input);
    }

    public function helloArray(Request $request): string
    {
        $names = $request->input("products.*.name");
        return json_encode($names);
    }

    public function helloArrayAll(Request $request): string
    {
        $names = $request->input("products.*.name");
        $prices = $request->input("products.*.price");
//        return json_encode(array_combine($names, $prices));
//        return json_encode([
//            'names' => $names,
//            'prices' => $prices
//        ]);
        return json_encode($request->all());
    }

    public function helloQuery(Request $request): string
    {
        $firstName = $request->query('first');
        $lastName = $request->query('last');
        $fullName = $firstName . ' ' . $lastName;

        return json_encode($fullName);
    }

    public function inputType(Request $request): string
    {
        $name = $request->input('name');
        $boolean = $request->boolean('married');
        $birthDate = $request->date('birth_date', 'Y-m-d');

        return json_encode([
            'name' => $name,
            'boolean' => $boolean,
            'birth_date' => $birthDate->format('Y-m-d'),
        ]);
    }

    public function filterOnly(Request $request): string
    {
        $name = $request->only(['name.first', 'name.last']);
        return json_encode($name);
    }

    public function filterExcept(Request $request): string
    {
        $user = $request->except("admin");
        return json_encode($user);
    }

    public function filterMerge(Request $request): string
    {
//        $request->mergeIfMissing([
//            'admin' => false
//        ]);
        $request->merge([
            'admin' => false
        ]);
        $user = $request->input();
        return json_encode($user);
    }
}
