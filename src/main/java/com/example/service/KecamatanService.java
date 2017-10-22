package com.example.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.example.model.KecamatanModel;

public interface KecamatanService {
	String namaKecamatanId (int id);
	List<KecamatanModel> selectKecamatanIdKota(int id);
	KecamatanModel selectKecamatan(int id);
	
	String namaKecamatanByNKK(String nomor_kk);
}
