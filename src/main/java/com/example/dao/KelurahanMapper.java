package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.model.KelurahanModel;

@Mapper
public interface KelurahanMapper {
	@Select("select * from kelurahan where kelurahan.id_kecamatan = #{id}")
	List<KelurahanModel> selectKelurahanIdKec(int id);
	
	@Select("select * from kelurahan where id = #{id}")
	KelurahanModel selectKelurahan(@Param("id") int id);
	
	@Select("SELECT id FROM kelurahan WHERE #{nama_kelurahan} = nama_kelurahan")
 	String kodeKelurahan(@Param("nama_kelurahan") String nama_kelurahan);
	
	@Select("SELECT nama_kelurahan FROM kelurahan kel, keluarga k WHERE k.id = #{id} AND kel.id = k.id_kelurahan")
 	String namaKelurahan(@Param("id") int id);
	
	@Select("select nama_kelurahan FROM kelurahan kel, keluarga k WHERE k.nomor_kk = #{nomor_kk} and k.id_kelurahan = kel.id")
	String namaKelurahanByNKK(@Param("nomor_kk") String nomor_kk);
}
