package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.model.KotaModel;


@Mapper
public interface KotaMapper {
	@Select("select * from kota")
	List<KotaModel> selectAllKota();
	
	@Select("select * from kota where id = #{id}")
	KotaModel selectKota(@Param("id") int id);
	
	@Select("select nama_kota FROM kota kot, kecamatan kec, kelurahan kel, keluarga k WHERE k.nomor_kk = #{nomor_kk} AND k.id_kelurahan = kel.id AND kel.id_kecamatan = kec.id AND kec.id_kota = kot.id")
	String namaKotaByNKK(@Param("nomor_kk") String nomor_kk);
	
	@Select("select nama_kota FROM kota WHERE id = #{id}")
	String namaKotaId(@Param("id") int id);
}
