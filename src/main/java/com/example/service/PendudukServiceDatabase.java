package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.PendudukMapper;
import com.example.model.PendudukModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PendudukServiceDatabase implements PendudukService {
	@Autowired
	private PendudukMapper pendudukMapper;
	
	@Override
	public PendudukModel selectPenduduk(String nik) {
		log.info ("select penduduk with nik {}", nik);
		return pendudukMapper.selectPenduduk (nik);
	}

	@Override
	public void tambahPenduduk(PendudukModel penduduk) {
		log.info ("Menambahkan penduduk {}", penduduk.getNik());
		pendudukMapper.tambahPenduduk(penduduk);
	}

	@Override
	public String kodeKecamatan(int id_keluarga) {
		log.info("Menambahkan nik dengan kode kecamatan {}", id_keluarga);
		return pendudukMapper.kodeKecamatan(id_keluarga);
	}
	public int hitungNIK(String nik) {
		log.info("menghitung nik penduduk {}", nik);
		return pendudukMapper.hitungNIK(nik);
	}

	@Override
	public void updatePenduduk(PendudukModel penduduk) {
		log.info("mengubah penduduk dengan nik{}", penduduk.getNik());
		pendudukMapper.updatePenduduk(penduduk);
		
	}

	@Override
	public int statusWafat(String nik) {
		log.info("mengubah status wafat dengan nik {}", nik);
		return pendudukMapper.statusWafat(nik);
	}

	@Override
	public int statusKel(String id_keluarga) {
		log.info("mengubah status keluarga dengan id {}", id_keluarga);
		return pendudukMapper.statusKel(id_keluarga);
	}

	@Override
	public List<PendudukModel> selectPendudukIdKel(int id) {
		// TODO Auto-generated method stub
		return pendudukMapper.selectPendudukIdKec(id);
	}

}
