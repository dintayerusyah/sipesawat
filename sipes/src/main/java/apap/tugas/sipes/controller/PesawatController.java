package apap.tugas.sipes.controller;

import apap.tugas.sipes.model.*;
import apap.tugas.sipes.service.*;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;
import java.util.ArrayList;

@Controller
public class PesawatController {
    @Autowired
    private PesawatService pesawatService;

    @Autowired
    private TipeService tipeService;

    @Autowired
    private TeknisiService teknisiService;

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
            model.addAttribute("pesawat", pesawat);
            return "view-pesawat";
        }
        catch(Exception NoSuchElementException){
            return "resep-not-found";
        }
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
}
