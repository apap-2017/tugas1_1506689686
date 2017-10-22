package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.PendudukMapper;
import com.example.model.KeluargaModel;
import com.example.model.KelurahanModel;
import com.example.model.PendudukModel;

import com.example.dao.KeluargaMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class KeluargaServiceDatabase implements KeluargaService {
	@Autowired
	private KeluargaMapper keluargaMapper;
	
	@Override
	public KeluargaModel selectIdKeluarga(int id) {
		log.info ("select keluarga with id {}", id);
		return keluargaMapper.selectIdKeluarga (id);
	}

	@Override
	public KeluargaModel selectKeluarga(String nomor_kk) {
		log.info("select keluarga with nkk {}", nomor_kk);
		return keluargaMapper.selectKeluarga(nomor_kk);
	}

	@Override
	public void tambahKeluarga(KeluargaModel keluarga) {
		log.info("menambahkan keluarga {}", keluarga.getNomor_kk());
		keluargaMapper.tambahKeluarga(keluarga);
		
	}

	@Override
	public int hitungNKK(String nomor_kk) {
		log.info("menghitung nkk keluarga {}", nomor_kk);
		return keluargaMapper.hitungNKK(nomor_kk);
	}

	@Override
	public String kodeKecamatan(String nama_kecamatan) {
		log.info("Menambahkan nkk dengan kode kecamatan {}", nama_kecamatan);
		return keluargaMapper.kodeKecamatan(nama_kecamatan);
	}

	@Override
	public void updateKeluarga(KeluargaModel keluarga) {
		log.info("mengubah penduduk dengan nomor_kk {}", keluarga.getNomor_kk());
		keluargaMapper.updateKeluarga(keluarga);
		
	}

	@Override
	public List<PendudukModel> selectAllPendudukWithidKeluarga() {
		log.info("melihat semua penduduk yang merupakan anggota keluarga");
		return keluargaMapper.selectAllPendudukWithidKeluarga();
	}

	@Override
	public int statusBerlaku(String id) {
		log.info("mengubah staus berlaku menjadi 1 dengan id keluarga{}", id);
		return keluargaMapper.statusBerlaku(id);
	}



}
