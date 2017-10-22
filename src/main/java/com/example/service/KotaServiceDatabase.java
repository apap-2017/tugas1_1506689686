package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.PendudukMapper;
import com.example.model.KecamatanModel;
import com.example.model.KeluargaModel;
import com.example.model.KelurahanModel;
import com.example.model.KotaModel;
import com.example.model.PendudukModel;
import com.example.dao.KecamatanMapper;
import com.example.dao.KeluargaMapper;
import com.example.dao.KotaMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class KotaServiceDatabase implements KotaService {
	@Autowired
	private KotaMapper kotaMapper;
	
	@Override
	public KotaModel selectKota(int id) {
		log.info ("select kota with id {}", id);
		return kotaMapper.selectKota (id);
	}

	@Override
	public String namaKotaByNKK(String nomor_kk) {
		log.info("select kota with nomor_kk {}", nomor_kk);
		return kotaMapper.namaKotaByNKK(nomor_kk);
	}

	@Override
	public List<KotaModel> selectAllKota() {
		// TODO Auto-generated method stub
		return kotaMapper.selectAllKota();
	}

	@Override
	public String namaKotaId(int id) {
		// TODO Auto-generated method stub
		return kotaMapper.namaKotaId(id);
	}




}
