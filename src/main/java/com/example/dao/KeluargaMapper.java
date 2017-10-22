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

import com.example.model.KeluargaModel;
import com.example.model.PendudukModel;
	
@Mapper
public interface KeluargaMapper {
	@Select("select * from keluarga where id = #{id}")
	KeluargaModel selectIdKeluarga(@Param("id") int id);
	
	@Select("select * from keluarga where nomor_kk = #{nomor_kk}")
	@Results(value = {
			@Result(property="id", column="id"),
			@Result(property="nomor_kk", column="nomor_kk"),
			@Result(property="alamat", column="alamat"),
			@Result(property="rt", column="rt"),
			@Result(property="rw", column="rw"),
			@Result(property="is_tidak_berlaku", column="is_tidak_berlaku"),			
			@Result(property="penduduk", column="id", javaType = List.class, many=@Many(select="selectAllPendudukWithidKeluarga"))})
	KeluargaModel selectKeluarga(@Param("nomor_kk") String nomor_kk);
	
	@Select("select * from keluarga where nomor_kk = #{nomor_kk}")
	KeluargaModel selectKeluargaByKelurahan(@Param("id_keluarga") String id_kelurahan);
	
	
    @Select("select * from penduduk where penduduk.id_keluarga = #{id_keluarga}")
    List<PendudukModel> selectAllPendudukWithidKeluarga();
    
    @Insert("INSERT INTO keluarga (nomor_kk, alamat, rt, rw, id_kelurahan, is_tidak_berlaku) VALUES (#{nomor_kk}, #{alamat}, #{rt}, #{rw}, #{id_kelurahan}, #{is_tidak_berlaku})")
    void tambahKeluarga(KeluargaModel keluarga);
    
	@Select("SELECT count(*) FROM keluarga where keluarga.nomor_kk LIKE #{nomor_kk}")
	int hitungNKK (String nomor_kk);
    
	@Select("SELECT kec.kode_kecamatan FROM kecamatan kec WHERE #{nama_kecamatan} = kec.nama_kecamatan")
 	String kodeKecamatan(@Param("nama_kecamatan") String nama_kecamatan);
	
    @Update("UPDATE keluarga SET nomor_kk= #{nomor_kk}, alamat= #{alamat}, rt= #{rt}, rw = #{rw}, id_kelurahan = #{id_kelurahan} WHERE id = #{id}")
    void updateKeluarga (KeluargaModel keluarga);
    
    @Update("UPDATE keluarga SET is_tidak_berlaku = 1 WHERE id = #{id}")
    int statusBerlaku(@Param("id") String id);
}
