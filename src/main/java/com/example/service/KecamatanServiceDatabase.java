package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.PendudukMapper;
import com.example.model.KecamatanModel;
import com.example.model.KeluargaModel;
import com.example.model.KelurahanModel;
import com.example.model.PendudukModel;
import com.example.dao.KecamatanMapper;
import com.example.dao.KeluargaMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class KecamatanServiceDatabase implements KecamatanService {
	@Autowired
	private KecamatanMapper kecamatanMapper;
	
	@Override
	public KecamatanModel selectKecamatan(int id) {
		log.info ("select kecamatan with id {}", id);
		return kecamatanMapper.selectKecamatan (id);
	}

	@Override
	public String namaKecamatanByNKK(String nomor_kk) {
		log.info("select kecamatan with nomor_kk {}", nomor_kk);
		return kecamatanMapper.namaKecamatanByNKK(nomor_kk);
	}

	@Override
	public List<KecamatanModel> selectKecamatanIdKota(int id) {
		// TODO Auto-generated method stub
		return kecamatanMapper.selectKecamatanIdKota(id);
	}

	@Override
	public String namaKecamatanId(int id) {
		// TODO Auto-generated method stub
		return kecamatanMapper.namaKecamatanId(id);
	}




}
