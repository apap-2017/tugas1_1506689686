package com.example.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.KecamatanModel;
import com.example.model.KeluargaModel;
import com.example.model.KelurahanModel;
import com.example.model.KotaModel;
import com.example.model.PendudukModel;
import com.example.service.KecamatanService;
import com.example.service.KeluargaService;
import com.example.service.KelurahanService;
import com.example.service.KotaService;
import com.example.service.PendudukService;

@Controller
public class KeluargaController {
	
	@Autowired
	KeluargaService keluargaDAO;
	
	@Autowired
	KelurahanService kelurahanDAO;
	
	@Autowired
	KecamatanService kecamatanDAO;
	
	@Autowired
	KotaService kotaDAO;
	
	@Autowired
	PendudukService pendudukDAO;
	
	@RequestMapping("/keluarga")
    public String keluargaNKK (@RequestParam(value = "nomor_kk", required = true) String nomor_kk, Model model) {
		KeluargaModel keluarga = keluargaDAO.selectKeluarga (nomor_kk);
		
        if (keluarga == null) {
            model.addAttribute ("nomor_kk", nomor_kk);
            return "not-found";
        } else {
        		
        		KelurahanModel kelurahan = kelurahanDAO.selectKelurahan(Integer.parseInt(keluarga.getId_kelurahan()));
        		KecamatanModel kecamatan = kecamatanDAO.selectKecamatan(Integer.parseInt(kelurahan.getId_kecamatan()));
        		KotaModel kota = kotaDAO.selectKota(Integer.parseInt(kecamatan.getId_kota()));
        		model.addAttribute("keluarga", keluarga);
        		model.addAttribute ("kelurahan", kelurahan);
            model.addAttribute ("kecamatan", kecamatan);
            model.addAttribute ("kota", kota);
            
            return "lihatDataKeluarga";
        }
    }
	
	@RequestMapping("/keluarga/tambah")
    public String add ()
    {
        return "form-add-keluarga";
    }
	
	@RequestMapping(value= {"/keluarga/tambah"}, method = RequestMethod.POST)
	public String addSubmit( Model model,
			@RequestParam(value = "alamat", required = true) String alamat,
			@RequestParam(value = "rt", required = true) String rt,
			@RequestParam(value = "rw", required = true) String rw,
			@RequestParam(value = "nama_kelurahan", required = true) String nama_kelurahan,
			@RequestParam(value = "nama_kecamatan", required = true) String nama_kecamatan,
			@RequestParam(value = "nama_kota", required = true) String nama_kota
			) {
		
		model.addAttribute("kelAdded", true);
		
		String kodeKecamatan = keluargaDAO.kodeKecamatan(nama_kecamatan);
		String nkkTemp = kodeKecamatan.substring(0, 6);
		
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		
		String fixedDate = format.format(date);
		
		
		
		String[] splitDate = fixedDate.split("-");
		String tanggal = splitDate[2];
		String bulan = splitDate[1];
		String tahun = splitDate[0].substring(2, 4);
		
		String fixTgl = "" + tanggal + bulan + tahun;
		nkkTemp += fixTgl;
		
		int hitungNKK = keluargaDAO.hitungNKK(nkkTemp + "%")+1;
		if(hitungNKK / 10 == 0) {
			nkkTemp += "000" + hitungNKK;
		}else if(hitungNKK / 100 == 0) {
			nkkTemp += "00" + hitungNKK;
		}else if(hitungNKK / 1000 == 0) {
			nkkTemp += "0" + hitungNKK;
		}
		
		nama_kelurahan = kelurahanDAO.kodeKelurahan(nama_kelurahan);
		
		KeluargaModel keluarga = new KeluargaModel (0, nkkTemp, alamat, rt, rw, nama_kelurahan, 0, null);
		keluargaDAO.tambahKeluarga(keluarga);
		
		model.addAttribute("keluarga", keluarga);
		return "form-add-keluarga";
		
		
	}
    @RequestMapping("/keluarga/ubah/{nomor_kk}")
    public String update (Model model, @PathVariable(value = "nomor_kk") String nomor_kk)
    {
        KeluargaModel keluarga = keluargaDAO.selectKeluarga (nomor_kk);


        if (keluarga != null) {
        	model.addAttribute("keluarga", keluarga);
        	model.addAttribute("kelurahan", kelurahanDAO.namaKelurahanByNKK(nomor_kk));
        	model.addAttribute("kecamatan", kecamatanDAO.namaKecamatanByNKK(nomor_kk));
        	model.addAttribute("kota", kotaDAO.namaKotaByNKK(nomor_kk));
        	
            return "form-update-keluarga";
        } else {
            model.addAttribute ("nomor_kk", nomor_kk);
            return "not-found";
        }
    }
	
    @RequestMapping(value = "/keluarga/ubah/{nomor_kk}", method = RequestMethod.POST)
    public String updateSubmit (Model model, KeluargaModel keluarga,
    			@RequestParam(value = "nama_kelurahan", required = true) String nama_kelurahan,
			@RequestParam(value = "nama_kecamatan", required = true) String nama_kecamatan
    		)
    {
    	model.addAttribute("kelChange", true);
    	String kodeKecamatan = keluargaDAO.kodeKecamatan(nama_kecamatan);
		String nkkTemp = kodeKecamatan.substring(0, 6);
		String nkkAwal = nkkTemp;
		
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		
		String fixedDate = format.format(date);
		
		
		
		String[] splitDate = fixedDate.split("-");
		String tanggal = splitDate[2];
		String bulan = splitDate[1];
		String tahun = splitDate[0].substring(2, 4);
		
		String fixTgl = "" + tanggal + bulan + tahun;
		nkkTemp += fixTgl;
		
		int hitungNKK = keluargaDAO.hitungNKK(nkkTemp + "%")+1;
		if(hitungNKK / 10 == 0) {
			nkkTemp += "000" + hitungNKK;
		}else if(hitungNKK / 100 == 0) {
			nkkTemp += "00" + hitungNKK;
		}else if(hitungNKK / 1000 == 0) {
			nkkTemp += "0" + hitungNKK;
		}
		
		nama_kelurahan = kelurahanDAO.kodeKelurahan(nama_kelurahan);
		keluarga.setNomor_kk(nkkTemp);
		keluarga.setId_kelurahan(nama_kelurahan);
		
		List<PendudukModel> listAnggota = keluargaDAO.selectAllPendudukWithidKeluarga();
		
		for(int i= 0; i<listAnggota.size(); i++) {
			PendudukModel tempPenduduk = listAnggota.get(i);
			String newNIK = nkkAwal + tempPenduduk.getNik().substring(6,12);
			int hitungNIK = pendudukDAO.hitungNIK(newNIK + "%")+1;
			
			String urutanNIK = "" + hitungNIK;
			if(hitungNIK / 10 == 0) {
				newNIK += "000" + urutanNIK;
			}else if(hitungNIK / 100 == 0) {
				newNIK += "00" + urutanNIK;
			}else if(hitungNIK / 1000 == 0) {
				newNIK += "0" + urutanNIK;
			}
			
			tempPenduduk.setNik(newNIK);
			pendudukDAO.updatePenduduk(tempPenduduk);
		}
		
		keluargaDAO.updateKeluarga(keluarga);
		model.addAttribute("keluarga", keluarga);
		return "form-update-keluarga";
    }
}
