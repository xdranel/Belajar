<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\Mahasiswa;
use Illuminate\Support\Facades\Validator;

class MahasiswaController extends Controller
{
    public function index()
    {
        $mahasiswas = Mahasiswa::all();
        if (request()->segment(1) == 'api') {
            return response()->json([
                'status' => 'success',
                'message' => 'Daftar Mahasiswa Berhasil Diambil',
                'data' => $mahasiswas
            ]);
        }
        return view('mahasiswa.index',['mahasiswas' => $mahasiswas]);
    }

    public function create()
    {
        return view('mahasiswa.create');
    }

    public function store(Request $request)
    {
        $rules = [
            'nim'           => 'required|size:8|unique:mahasiswas',
            'nama'          => 'required|min:3|max:50',
            'jenis_kelamin' => 'required|in:P,L',
            'jurusan'       => 'required',
            'alamat'        => '',
        ];

        $validator = Validator::make($request->all(), $rules);

        if ($validator->fails()) {
            if (request()->segment(1) == 'api') {
                return response()->json([
                    'status' => 'error',
                    'message' => 'Validasi Gagal',
                    'errors' => $validator->errors()
                ], 422);
            }
            return redirect()->back()->withErrors($validator)->withInput();
        }

        $validateData = $validator->validated();

        Mahasiswa::create($validateData);

        if (request()->segment(1) == 'api') {
            return response()->json([
                'status' => 'success',
                'message' => 'Data Mahasiswa Berhasil Ditambahkan',
                'data' => $validateData
            ]);
        }
        return redirect()->route('mahasiswas.index')->with('pesan',"Penambahan data {$validateData['nama']} berhasil");
    }

    public function show($id)
    {
        $mahasiswa = Mahasiswa::find($id);

        if (request()->segment(1) == 'api') {
            if (!$mahasiswa) {
                return response()->json([
                    'status' => 'error',
                    'message' => 'Data Mahasiswa Tidak Ditemukan',
                    'data' => null
                ], 404);
            }
            return response()->json([
                'status' => 'success',
                'message' => 'Data Detail Mahasiswa Berhasil Ditampilkan',
                'data' => $mahasiswa
            ]);
        }

        if (!$mahasiswa) {
            abort(404);
        }

        return view('mahasiswa.show',['mahasiswa' => $mahasiswa]);
    }

    public function edit($id)
    {
        $mahasiswa = Mahasiswa::find($id);

        if (request()->segment(1) == 'api') {
            if (!$mahasiswa) {
                return response()->json([
                    'status' => 'error',
                    'message' => 'Data Mahasiswa Tidak Ditemukan',
                    'data' => null
                ], 404);
            }
            return response()->json([
                'status' => 'success',
                'message' => 'Data Mahasiswa Ditemukan',
                'data' => $mahasiswa
            ]);
        }

        if (!$mahasiswa) {
            abort(404);
        }

        return view('mahasiswa.edit',['mahasiswa' => $mahasiswa]);
    }

    public function update(Request $request, $id)
    {
        $mahasiswa = Mahasiswa::find($id);

        if (request()->segment(1) == 'api') {
            if (!$mahasiswa) {
                return response()->json([
                    'status' => 'error',
                    'message' => 'Data Mahasiswa Tidak Ditemukan',
                    'data' => null
                ], 404);
            }
        }

        if (!$mahasiswa) {
            abort(404);
        }

        $rules = [
            'nim'           => 'required|size:8|unique:mahasiswas,nim,'.$mahasiswa->id,
            'nama'          => 'required|min:3|max:50',
            'jenis_kelamin' => 'required|in:P,L',
            'jurusan'       => 'required',
            'alamat'        => '',
        ];

        $validator = Validator::make($request->all(), $rules);

        if ($validator->fails()) {
            if (request()->segment(1) == 'api') {
                return response()->json([
                    'status' => 'error',
                    'message' => 'Validasi Gagal',
                    'errors' => $validator->errors()
                ], 422);
            }
            return redirect()->back()->withErrors($validator)->withInput();
        }

        $validateData = $validator->validated();

        $mahasiswa->update($validateData);

        if (request()->segment(1) == 'api') {
            return response()->json([
                'status' => 'success',
                'message' => 'Data Mahasiswa Berhasil Diupdate',
                'data' => $validateData
            ]);
        }

        return redirect()->route('mahasiswas.show',['mahasiswa' => $mahasiswa->id])
        ->with('pesan',"Update data {$validateData['nama']} berhasil");
    }

    public function destroy($id)
    {
        $mahasiswa = Mahasiswa::find($id);

        if (request()->segment(1) == 'api') {
            if (!$mahasiswa) {
                return response()->json([
                    'status' => 'error',
                    'message' => 'Data Mahasiswa Tidak Ditemukan',
                    'data' => null
                ], 404);
            }
        }

        if (!$mahasiswa) {
            abort(404);
        }

        $mahasiswa->delete();

        if (request()->segment(1) == 'api') {
            return response()->json([
                'status' => 'success',
                'message' => 'Data Mahasiswa Berhasil Dihapus',
                'data' => $mahasiswa
            ]);
        }
        return redirect()->route('mahasiswas.index')
        ->with('pesan',"Hapus data $mahasiswa->nama berhasil");
    }

}