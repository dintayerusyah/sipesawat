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
            model.addAttribute("penerbanganMessage", "Penerbangan dengan nomor " + penerbangan.getNomorPenerbangan() + " sudah terdaftar.");
            model.addAttribute("title", "Gagal Menyimpan Data Penerbangan!");
            return "submit-message-penerbangan";
        }
        else{
            penerbanganService.addPenerbangan(penerbangan);
            model.addAttribute("penerbangan", penerbangan);
            model.addAttribute("title", "Berhasil Menyimpan Data Penerbangan!");
            model.addAttribute("penerbanganMessage", "Penerbangan dengan nomor " + penerbangan.getNomorPenerbangan() + " berhasil ditambahkan.");
        }
        return "submit-message-penerbangan";
    }

    @GetMapping("/penerbangan/ubah/{id}")
    public String updatePenerbangan(
        @PathVariable Long id,
        Model model
    ){
        try{
            model.addAttribute("penerbangan", penerbanganService.getPenerbanganById(id));
            return "form-update-penerbangan";
        }
        catch(Exception NoSuchElementException){
            return "not-found";
        }
    }

    @PostMapping("/penerbangan/ubah/{id}")
    public String submitUpdatePenerbangan(
        @ModelAttribute PenerbanganModel penerbangan,
        @PathVariable Long id,
        Model model
    ){
        PenerbanganModel updatedPenerbangan = penerbanganService.updatePenerbangan(id,penerbangan);
        model.addAttribute("title", "Berhasil Menyimpan Data Penerbangan!");
        model.addAttribute("penerbanganMessage", "Penerbangan dengan nomor " + updatedPenerbangan.getNomorPenerbangan() + " berhasil ditambahkan.");
        return "submit-message-penerbangan";
    }

    @GetMapping("penerbangan/hapus/{id}")
    public String deletePenerbangan(
        @PathVariable Long id,
        Model model
    ){
        // System.out.println(id);
        PenerbanganModel penerbangan = penerbanganService.getPenerbanganById(id);
        model.addAttribute("title", "Penerbangan Dihapus!");
        model.addAttribute("penerbanganMessage", "Penerbangan " + penerbangan.getNomorPenerbangan() + " berhasil dihapus.");
        penerbanganService.deletePenerbangan(penerbangan);
        return "submit-message-penerbangan";
    }
}