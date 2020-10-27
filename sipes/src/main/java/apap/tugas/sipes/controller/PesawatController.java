package apap.tugas.sipes.controller;

import apap.tugas.sipes.model.*;
import apap.tugas.sipes.service.*;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;

@Controller
public class PesawatController {
    @Autowired
    private PesawatService pesawatService;

    @GetMapping("/")
    private String home(){return "home";}

    @GetMapping("/pesawat")
    private String viewAllPesawat(Model model){
        List<PesawatModel> daftarPesawat = pesawatService.getPesawatList();
        model.addAttribute("daftarPesawat", daftarPesawat);
        return "viewall-pesawat";
    }
}
