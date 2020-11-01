package apap.tugas.sipes.controller;

import apap.tugas.sipes.model.*;
import apap.tugas.sipes.service.*;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;

@Controller
public class PesawatController {
    @Autowired
    private PesawatService pesawatService;

    @Autowired
    private TipeService tipeService;

    @Autowired
    private TeknisiService teknisiService;

    @Autowired
    private PenerbanganService penerbanganService;

    // private List<TeknisiModel> teknisiRows = new ArrayList<TeknisiModel>();

    @GetMapping("/")
    private String home(){return "home";}

    @GetMapping("/pesawat")
    private String viewAllPesawat(Model model){
        List<PesawatModel> daftarPesawat = pesawatService.getPesawatList();
        model.addAttribute("daftarPesawat", daftarPesawat);
        return "viewall-pesawat";
    }

    @GetMapping("/pesawat/tambah")
    private String formTambahPesawat(
        Model model
    ){  
        List<TeknisiModel> teknisiRows = new ArrayList<TeknisiModel>();
        teknisiRows.add(new TeknisiModel());
        PesawatModel pesawat = new PesawatModel();
        pesawat.setListTeknisi(teknisiRows);
        model.addAttribute("pesawat", pesawat);
        model.addAttribute("daftarTipe", tipeService.getTipeList());
        model.addAttribute("daftarTeknisi", teknisiService.getTeknisiList());
        return "form-add-pesawat";
    }

    @RequestMapping(value="/pesawat/tambah", params={"save"}, method = RequestMethod.POST)
    private String submitFormTambahPesawat(
        @ModelAttribute PesawatModel pesawat,
        Model model
    ){
        String nomorSeri = pesawatService.generateNomorSeri(pesawat);
        pesawat.setNomorSeri(nomorSeri);
        pesawatService.addPesawat(pesawat);
        model.addAttribute("pesawat", pesawat);
        return "submit-add-pesawat";
    }

    @RequestMapping(value="/pesawat/tambah", params={"addRow"}, method = RequestMethod.POST)
    private String addTeknisiRow(
        @ModelAttribute PesawatModel pesawat,
        Model model
    ){
        model.addAttribute("pesawat", pesawat);
        if (pesawat.getListTeknisi() == null || pesawat.getListTeknisi().size() == 0) {
            pesawat.setListTeknisi(new ArrayList<TeknisiModel>());
        }
        pesawat.getListTeknisi().add(new TeknisiModel());
        model.addAttribute("daftarTipe", tipeService.getTipeList());
        model.addAttribute("daftarTeknisi", teknisiService.getTeknisiList());
        return "form-add-pesawat";
    }

    @GetMapping("/pesawat/{id}")
    public String viewPesawat(
        @PathVariable Long id, Model model
    ){
        try{
            PesawatModel pesawat = pesawatService.getPesawatById(id);
            List<PenerbanganModel> allPenerbangan = penerbanganService.getPenerbanganList();
            model.addAttribute("pesawat", pesawat);
            model.addAttribute("allPenerbangan", allPenerbangan);
            return "view-pesawat";
        }
        catch(Exception NoSuchElementException){
            return "not-found";
        }
    }

    @RequestMapping(value="/pesawat/{id}/tambah-penerbangan", params={"idPenerbangan"}, method = RequestMethod.POST)
    public String addPenerbanganToPesawat(
        @PathVariable Long id, 
        Model model,
        @RequestParam("idPenerbangan")String idPenerbangan,
        @ModelAttribute PesawatModel pesawat
    ){
        // PesawatModel pesawat = pesawatService.getPesawatById(id);
        // Long flightId = Long.parseLong(idPenerbangan);
        // PenerbanganModel penerbangan = penerbanganService.getPenerbanganById(flightId);
        // pesawatService.addPenerbangan(pesawat, penerbangan);
        // PesawatModel updatedPesawat = pesawatService.getPesawatById(id);
        // List<PenerbanganModel> allPenerbangan = penerbanganService.getPenerbanganList();
        // model.addAttribute("allPenerbangan", allPenerbangan);
        // model.addAttribute("pesawat", updatedPesawat);
        // model.addAttribute("successMessage", "Penerbangan dengan nomor " + penerbangan.getNomorPenerbangan() + " berhasil ditambahkan.");

        //Add atribut successMessage, pesawat (yang sudah diupdate), allPenerbangan
        PenerbanganModel penerbangan = penerbanganService.getPenerbanganById(Long.parseLong(idPenerbangan));
        PenerbanganModel addedPenerbangan = penerbanganService.addPenerbanganToPesawat(pesawat, penerbangan);
        PesawatModel updatedPesawat = pesawatService.getPesawatById(id);
        model.addAttribute("pesawat", updatedPesawat);
        model.addAttribute("successMessage", "Penerbangan dengan nomor " + addedPenerbangan.getNomorPenerbangan() + " berhasil ditambahkan.");
        model.addAttribute("allPenerbangan", penerbanganService.getPenerbanganList());
        return "view-pesawat";
    }

    @GetMapping("/pesawat/ubah/{id}")
    public String updatePesawat(
        @PathVariable Long id,
        Model model
    ){
        PesawatModel pesawat = pesawatService.getPesawatById(id);
        model.addAttribute("pesawat", pesawat);
        return "form-update-pesawat";
    }

    @PostMapping("/pesawat/ubah")
    public String submitUpdatePesawat(
        @ModelAttribute PesawatModel pesawat, Model model
    ){
        String nomorSeri = pesawatService.generateNomorSeri(pesawat);
        pesawat.setNomorSeri(nomorSeri);
        PesawatModel updatedPesawat = pesawatService.updatePesawat(pesawat);  
        model.addAttribute("pesawat", updatedPesawat);
        return "submit-update-pesawat";
    }

    @GetMapping("/pesawat/pesawat-tua")
    public String viewPesawatTua(
        Model model
    ){
        List<PesawatModel> daftarPesawatTua = pesawatService.getPesawatTua(LocalDate.now());
        model.addAttribute("daftarPesawatTua", daftarPesawatTua);
        model.addAttribute("daftarUsia", pesawatService.getAges(daftarPesawatTua));
        return "viewall-pesawat-tua";
    }

    @GetMapping("/bonus")
    public String bonus(
        Model model
    ){
        List<PesawatModel> daftarPesawat = pesawatService.getPesawatList();
        model.addAttribute("daftarPesawat", daftarPesawat);
        List<Integer> listTotalTeknisi = pesawatService.getSumTeknisi(daftarPesawat);
        model.addAttribute("listTotalTeknisi", listTotalTeknisi);
        return "bonus";
    }

    @GetMapping("pesawat/hapus/{id}")
    public String deletePesawat(
        @PathVariable Long id,
        Model model
    ){
        PesawatModel pesawat = pesawatService.getPesawatById(id);
        model.addAttribute("title", "Pesawat Dihapus!");
        String pesawatMessage = "Pesawat " + pesawat.getMaskapai() + " dengan nomor seri " + pesawat.getNomorSeri() + " berhasil dihapus.";
        model.addAttribute("pesawatMessage", pesawatMessage);
        pesawatService.deletePesawat(pesawat);
        return "submit-delete-pesawat";
    }
}
