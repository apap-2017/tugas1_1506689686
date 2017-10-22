package com.example.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.example.model.PendudukModel;

public interface PendudukService {
	PendudukModel selectPenduduk(String nik);
	
	void tambahPenduduk(PendudukModel penduduk);
	
	String kodeKecamatan(int id_keluarga);
	
	int hitungNIK(String nik);
	
	void updatePenduduk (PendudukModel penduduk);
	
	int statusWafat(@Param("nik") String nik);
	
	int statusKel (@Param("id_keluarga") String id_keluarga);
	List<PendudukModel> selectPendudukIdKel(int id);
//	KelurahanModel lokasiKel(String nik);
//	KecamatanModel lokasiKec(String nik);
//	KotaModel lokasiKt(String nik);
}
