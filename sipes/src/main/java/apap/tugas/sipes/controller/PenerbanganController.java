package apap.tugas.sipes.controller;

import apap.tugas.sipes.model.PenerbanganModel;
import apap.tugas.sipes.service.PenerbanganService;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;
import java.util.ArrayList;

@Controller
public class PenerbanganController {
    @Autowired
    private PenerbanganService penerbanganService;

    @GetMapping("/penerbangan")
    public String viewAllPenerbangan(Model model){
        model.addAttribute("daftarPenerbangan", penerbanganService.getPenerbanganList());
        return "viewall-penerbangan";
    }

    @GetMapping("/penerbangan/{id}")
    public String viewPenerbangan(
        @PathVariable Long id, Model model
    ){
        try{
            PenerbanganModel penerbangan = penerbanganService.getPenerbanganById(id);
            model.addAttribute("penerbangan", penerbangan);
            return "view-penerbangan";
        }
        catch(Exception NoSuchElementException){
            return "not-found";
        }
        
    }

    @GetMapping("/penerbangan/tambah")
    public String formTambahPenerbangan(
        Model model
    ){
        model.addAttribute("penerbangan", new PenerbanganModel());
        return "form-add-penerbangan";
    }

    @PostMapping("penerbangan/tambah")
    public String submitFormTambahPenerbangan(
        @ModelAttribute PenerbanganModel penerbangan,
        Model model
    ){
        // Jika nomor penerbangan sudah terdaftar, gagal menyimpan data
        if (penerbanganService.checkPenerbanganByNomorPenerbangan(penerbangan.getNomorPenerbangan())){
            model.addAttribute("errorMessage", "Penerbangan dengan nomor " + penerbangan.getNomorPenerbangan() + " sudah terdaftar.");
            return "submit-failed-penerbangan";
        }
        else{
            penerbanganService.addPenerbangan(penerbangan);
            model.addAttribute("penerbangan", penerbangan);
            return "submit-add-penerbangan";
        }
    }
}
