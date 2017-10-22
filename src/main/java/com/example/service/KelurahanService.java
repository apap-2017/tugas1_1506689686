package com.example.service;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.example.model.KelurahanModel;


public interface KelurahanService {
	
	List<KelurahanModel> selectKelurahanIdKec(int id);
	
	KelurahanModel selectKelurahan(int id);
	
	String kodeKelurahan(String nama_kelurahan);
	
	String namaKelurahan(int id);
	
	String namaKelurahanByNKK(String nomor_kk);
	

}
