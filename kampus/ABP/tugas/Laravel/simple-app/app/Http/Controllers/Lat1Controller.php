<?php

namespace App\Http\Controllers;

class Lat1Controller
{
    public function index()
    {
        $data["nama"]="Agus";
        $data["asal"]="Bandung";
        return view('components.v_latihan1', $data);
    }

    public function method2(){
        $data['title'] = "Daftar Mahasiswa";
        $data['daf_mhs'] = array(
            array("nama" => "Agus", "asal" => "Bandung"),
            array("nama" => "Budi", "asal" => "Jakarta"),
            array("nama" => "Roni", "asal" => "Surabaya")
        );
        return view('components.v_latihan2', $data);
    }
}
