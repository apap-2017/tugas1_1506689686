package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.model.KecamatanModel;

@Mapper
public interface KecamatanMapper {
	@Select("select nama_kecamatan FROM kecamatan WHERE id = #{id}")
	String namaKecamatanId(@Param("id") int id);
	
	@Select("select * from kecamatan where kecamatan.id_kota = #{id}")
	List<KecamatanModel> selectKecamatanIdKota(int id);
	
	@Select("select * from kecamatan where id = #{id}")
	KecamatanModel selectKecamatan(@Param("id") int id);
	
	@Select("select nama_kecamatan FROM kecamatan kec, kelurahan kel, keluarga k WHERE k.nomor_kk = #{nomor_kk} AND k.id_kelurahan = kel.id AND kel.id_kecamatan = kec.id")
	String namaKecamatanByNKK(@Param("nomor_kk") String nomor_kk);
}
