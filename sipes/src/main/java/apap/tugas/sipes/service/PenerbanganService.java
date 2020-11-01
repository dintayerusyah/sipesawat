package apap.tugas.sipes.service;

import apap.tugas.sipes.model.PenerbanganModel;
import apap.tugas.sipes.model.PesawatModel;

import java.util.List;
import java.time.LocalDate;

public interface PenerbanganService {
    void addPenerbangan(PenerbanganModel penerbangan);

    PenerbanganModel updatePenerbangan(Long id, PenerbanganModel penerbangan);

    List<PenerbanganModel> getPenerbanganList();

    void deletePenerbangan(PenerbanganModel penerbangan);

    PenerbanganModel getPenerbanganById (Long id);

    boolean checkPenerbanganByNomorPenerbangan(String nomorPenerbangan);

    PenerbanganModel addPenerbanganToPesawat(PesawatModel pesawat, PenerbanganModel penerbangan);
}
