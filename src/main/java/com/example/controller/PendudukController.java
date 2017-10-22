package com.example.controller;

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
public class PendudukController {
	@Autowired
    PendudukService pendudukDAO;
	
	@Autowired
	KeluargaService keluargaDAO;
	
	@Autowired
	KelurahanService kelurahanDAO;
	
	@Autowired
	KecamatanService kecamatanDAO;
	
	@Autowired
	KotaService kotaDAO;
	
	@RequestMapping("/penduduk")
    public String pendudukNIK (@RequestParam(value = "nik", required = true) String nik, Model model) {
		PendudukModel penduduk = pendudukDAO.selectPenduduk (nik);
		
        if (penduduk == null) {
            model.addAttribute ("nik", nik);
            return "not-found";
        } else {
        		KeluargaModel keluarga = keluargaDAO.selectIdKeluarga(penduduk.getId_keluarga());
        		KelurahanModel kelurahan = kelurahanDAO.selectKelurahan(Integer.parseInt(keluarga.getId_kelurahan()));
        		KecamatanModel kecamatan = kecamatanDAO.selectKecamatan(Integer.parseInt(kelurahan.getId_kecamatan()));
        		KotaModel kota = kotaDAO.selectKota(Integer.parseInt(kecamatan.getId_kota()));
        		model.addAttribute("keluarga", keluarga);
            model.addAttribute ("penduduk", penduduk);
            model.addAttribute ("kelurahan", kelurahan);
            model.addAttribute ("kecamatan", kecamatan);
            model.addAttribute ("kota", kota);
            
            return "lihatDataPenduduk";
        }
    }
	
	@RequestMapping("/penduduk/tambah")
    public String add ()
    {
        return "form-add-penduduk";
    }
	
	@RequestMapping(value= {"/penduduk/tambah"}, method = RequestMethod.POST)
	public String addSubmit(@RequestParam(value = "nama", required = true) String nama,
			@RequestParam(value = "tempat_lahir", required = true) String tempat_lahir,
			@RequestParam(value = "tanggal_lahir", required = true) String tanggal_lahir,
			@RequestParam(value = "jenis_kelamin", required = true) int jenis_kelamin,
			@RequestParam(value = "is_wni", required = true) int is_wni,
			@RequestParam(value = "id_keluarga", required = true) int id_keluarga,
			@RequestParam(value = "agama", required = true) String agama,
			@RequestParam(value = "pekerjaan", required = true) String pekerjaan,
			@RequestParam(value = "status_perkawinan", required = true) String status_perkawinan,
			@RequestParam(value = "status_dalam_keluarga", required = true) String status_dalam_keluarga,
			@RequestParam(value = "golongan_darah", required = true) String golongan_darah,
			@RequestParam(value = "is_wafat", required = true) int is_wafat,
			Model model
			) {
		model.addAttribute("isAdded", true);
		
		String kodeKecamatan = pendudukDAO.kodeKecamatan(id_keluarga);
		String firstNIK = kodeKecamatan.substring(0, 6);
		
		String[] tanggalLahir = tanggal_lahir.split("-");
		String tanggal = tanggalLahir[2];
		String bulan = tanggalLahir[1];
		String tahun = tanggalLahir[0].substring(2, 4);
		
		
		if(jenis_kelamin == 1) {
			int tgl = Integer.parseInt(tanggal);
			tgl += 40;
			tanggal = "" + tgl;
		}
		
		String fixTgl = "" + tanggal + bulan + tahun;
		firstNIK += fixTgl;
		
		int hitungNIK = pendudukDAO.hitungNIK(firstNIK + "%")+1;
		
		String urutanNIK = "" + hitungNIK;
		if(hitungNIK / 10 == 0) {
			firstNIK += "000" + urutanNIK;
		}else if(hitungNIK / 100 == 0) {
			firstNIK += "00" + urutanNIK;
		}else if(hitungNIK / 1000 == 0) {
			firstNIK += "0" + urutanNIK;
		}
		
		
		PendudukModel penduduk = new PendudukModel (0, firstNIK, nama, tempat_lahir, tanggal_lahir, 
				jenis_kelamin, is_wni, id_keluarga, agama, pekerjaan, status_perkawinan, status_dalam_keluarga, 
				golongan_darah, is_wafat);
	    pendudukDAO.tambahPenduduk (penduduk);
		
	    model.addAttribute("penduduk", penduduk);
		
		return "form-add-penduduk";
		
		
	}
	
	
    @RequestMapping("/penduduk/ubah/{nik}")
    public String update (Model model, @PathVariable(value = "nik") String nik)
    {
        PendudukModel penduduk = pendudukDAO.selectPenduduk (nik);

        if (penduduk != null) {
        	model.addAttribute("penduduk", penduduk);
            return "form-update-penduduk";
        } else {
            model.addAttribute ("nik", nik);
            return "not-found";
        }
    }
    
    @RequestMapping(value = "/penduduk/ubah/{nik}", method = RequestMethod.POST)
    public String updateSubmit (Model model, PendudukModel penduduk)
    {
    		model.addAttribute("isChange", true);
    		String kodeKecamatan = pendudukDAO.kodeKecamatan(penduduk.getId_keluarga());
    		String firstNIK = kodeKecamatan.substring(0, 6);
		
		String[] tanggalLahir = penduduk.getTanggal_lahir().split("-");
		String tanggal = tanggalLahir[2];
		String bulan = tanggalLahir[1];
		String tahun = tanggalLahir[0].substring(2, 4);
		
		if(penduduk.getJenis_kelamin() == 1) {
			int tgl = Integer.parseInt(tanggal);
			tgl += 40;
			tanggal = "" + tgl;
		}
		
		String fixTgl = "" + tanggal + bulan + tahun;
		String newNIK = firstNIK + fixTgl;
		
		int hitungNIK = pendudukDAO.hitungNIK(newNIK + "%")+1;
		
		String urutanNIK = "" + hitungNIK;
		if(hitungNIK / 10 == 0) {
			newNIK += "000" + urutanNIK;
		}else if(hitungNIK / 100 == 0) {
			newNIK += "00" + urutanNIK;
		}else if(hitungNIK / 1000 == 0) {
			newNIK += "0" + urutanNIK;
		}
		
		penduduk.setNik(newNIK);
		
		pendudukDAO.updatePenduduk(penduduk);
		model.addAttribute("penduduk", penduduk);
		
        return "form-update-penduduk";
    }
    
    
	@RequestMapping(value ="/penduduk", method = RequestMethod.POST)
    public String gantiStatusWafat (Model model, @RequestParam(value = "nik", required = true) String nik) {
		
		PendudukModel penduduk = pendudukDAO.selectPenduduk(nik);
		pendudukDAO.statusWafat(nik);
		penduduk.setIs_wafat(1);
		
		if(pendudukDAO.statusKel(""+penduduk.getId_keluarga()) < 1){
			keluargaDAO.statusBerlaku(""+penduduk.getId_keluarga());
		}
		
		KeluargaModel keluarga = keluargaDAO.selectIdKeluarga(penduduk.getId_keluarga());
		KelurahanModel kelurahan = kelurahanDAO.selectKelurahan(Integer.parseInt(keluarga.getId_kelurahan()));
		KecamatanModel kecamatan = kecamatanDAO.selectKecamatan(Integer.parseInt(kelurahan.getId_kecamatan()));
		KotaModel kota = kotaDAO.selectKota(Integer.parseInt(kecamatan.getId_kota()));
		model.addAttribute("keluarga", keluarga);
		model.addAttribute ("penduduk", penduduk);
		model.addAttribute ("kelurahan", kelurahan);
		model.addAttribute ("kecamatan", kecamatan);
		model.addAttribute ("kota", kota);
		
		model.addAttribute("isWafat", true);
		
		return "lihatDataPenduduk";
    }
	
	
	@RequestMapping(value ="/penduduk/cari", method = RequestMethod.GET)
	public String cariData(Model model,
			@RequestParam(value = "kt", required = false) String kt,
			@RequestParam(value = "kc", required = false) String kc,
			@RequestParam(value = "kl", required = false) String kl) {	
		if(kl != null) {
			model.addAttribute("kl", kl);
			model.addAttribute("semuaPenduduk", pendudukDAO.selectPendudukIdKel(Integer.parseInt(kl)));
			return "cari-result";
		} else if(kc != null) {
			model.addAttribute("kc", kc);
			model.addAttribute("kt", kt);
			model.addAttribute("nama_kota", kotaDAO.namaKotaId(Integer.parseInt(kt)));
			model.addAttribute("nama_kecamatan", kecamatanDAO.namaKecamatanId(Integer.parseInt(kc)));
			model.addAttribute("semuaKelurahan", kelurahanDAO.selectKelurahanIdKec(Integer.parseInt(kc)));
			return "cari-kelurahan";
		} else if(kt != null) {
			model.addAttribute("kt", kt);
			model.addAttribute("nama_kota", kotaDAO.namaKotaId(Integer.parseInt(kt)));
			model.addAttribute("semuaKecamatan", kecamatanDAO.selectKecamatanIdKota(Integer.parseInt(kt)));
			return "cari-kecamatan";
		}
		model.addAttribute("semuaKota", kotaDAO.selectAllKota());
		return "cari-kota";
	}
	
}
