package com.example.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.example.model.KeluargaModel;
import com.example.model.KelurahanModel;
import com.example.model.PendudukModel;

public interface KeluargaService {
	KeluargaModel selectIdKeluarga(int id);
	KeluargaModel selectKeluarga(String nomor_kk);
	
	void tambahKeluarga(KeluargaModel keluarga);
	
	int hitungNKK (String nomor_kk);
	
	String kodeKecamatan(String nama_kecamatan);
	
	void updateKeluarga (KeluargaModel keluarga);
	
	List<PendudukModel> selectAllPendudukWithidKeluarga();
	
	int statusBerlaku(String id);
}
