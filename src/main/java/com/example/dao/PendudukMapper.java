package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.model.KelurahanModel;
import com.example.model.PendudukModel;

@Mapper
public interface PendudukMapper {
	@Select("SELECT * FROM penduduk where nik = #{nik}")
	PendudukModel selectPenduduk (@Param("nik") String nik);
	
	@Insert("INSERT INTO penduduk (nik, nama, tempat_lahir, "
			+ "tanggal_lahir, jenis_kelamin, is_wni, id_keluarga, agama, pekerjaan, status_perkawinan, "
			+ "status_dalam_keluarga, golongan_darah, is_wafat) VALUES (#{nik}, #{nama}, #{tempat_lahir},"
			+ " #{tanggal_lahir}, #{jenis_kelamin}, #{is_wni}, #{id_keluarga}, #{agama}, #{pekerjaan}, "
			+ "#{status_perkawinan}, #{status_dalam_keluarga}, #{golongan_darah}, #{is_wafat})")
    void tambahPenduduk (PendudukModel penduduk);
	
	@Select("SELECT count(*) FROM penduduk where penduduk.nik LIKE #{nik}")
	int hitungNIK (String nik);
	
	@Select("SELECT kec.kode_kecamatan FROM keluarga kg, kelurahan kel, kecamatan kec "
			+ " WHERE #{id_keluarga} = kg.id AND kg.id_kelurahan = kel.id AND kel.id_kecamatan = kec.id")
 	String kodeKecamatan(@Param("id_keluarga") int id_keluarga);
	
    @Update("UPDATE penduduk SET nik= #{nik}, nama= #{nama}, tempat_lahir= #{tempat_lahir}, tanggal_lahir = #{tanggal_lahir}, jenis_kelamin = #{jenis_kelamin}, is_wni = #{is_wni}, id_keluarga = #{id_keluarga}, agama = #{agama}, pekerjaan = #{pekerjaan}, status_perkawinan = #{status_perkawinan}, status_dalam_keluarga = #{status_dalam_keluarga}, golongan_darah = #{golongan_darah}, is_wafat = #{is_wafat} WHERE id = #{id}")
    void updatePenduduk (PendudukModel penduduk);
    
    @Update("UPDATE penduduk SET is_wafat = 1 WHERE nik = #{nik}")
    int statusWafat(@Param("nik") String nik);
    
    @Update("SELECT count(*) FROM penduduk WHERE id_keluarga = #{id_keluarga} AND is_wafat = 0")
    int statusKel (@Param("id_keluarga") String id_keluarga);
    
    @Select("select * from penduduk, kelurahan, keluarga where penduduk.id_keluarga = keluarga.id and keluarga.id_kelurahan = #{id}")
	List<PendudukModel> selectPendudukIdKec(int id);
}
