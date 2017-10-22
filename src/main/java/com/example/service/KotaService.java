package com.example.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.example.model.KotaModel;

public interface KotaService {

	String namaKotaId(int id);
	List<KotaModel> selectAllKota();
	
	KotaModel selectKota(int id);
	
	String namaKotaByNKK(String nomor_kk);
	
}
