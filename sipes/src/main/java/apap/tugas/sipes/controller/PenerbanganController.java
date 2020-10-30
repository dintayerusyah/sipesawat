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
}
