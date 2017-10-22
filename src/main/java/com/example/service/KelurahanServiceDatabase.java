package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.PendudukMapper;
import com.example.model.KeluargaModel;
import com.example.model.KelurahanModel;
import com.example.model.PendudukModel;

import com.example.dao.KeluargaMapper;
import com.example.dao.KelurahanMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class KelurahanServiceDatabase implements KelurahanService {
	@Autowired
	private KelurahanMapper kelurahanMapper;
	
	@Override
	public KelurahanModel selectKelurahan(int id) {
		log.info ("select kelurahan with id {}", id);
		return kelurahanMapper.selectKelurahan (id);
	}

	@Override
	public String kodeKelurahan(String nama_kelurahan) {
		log.info("melihat id kelurahan {}", nama_kelurahan);
		return kelurahanMapper.kodeKelurahan(nama_kelurahan);
	}

	@Override
	public String namaKelurahan(int id) {
		log.info("mengambil nama kelurahan dengan id {}", id);
		return kelurahanMapper.namaKelurahan(id);
	}

	@Override
	public String namaKelurahanByNKK(String nomor_kk) {
		log.info("mengambil nama kelurahan dengan nomor kk {}", nomor_kk);
		return kelurahanMapper.namaKelurahanByNKK(nomor_kk);
	}

	@Override
	public List<KelurahanModel> selectKelurahanIdKec(int id) {
		// TODO Auto-generated method stub
		log.info("{}",id);
		return kelurahanMapper.selectKelurahanIdKec(id);
	}


}
