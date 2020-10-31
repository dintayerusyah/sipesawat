package apap.tugas.sipes.service;

import apap.tugas.sipes.model.PenerbanganModel;

import java.util.List;

public interface PenerbanganService {
    void addPenerbangan(PenerbanganModel penerbangan);

    // void updatePenerbangan(PenerbanganModel penerbangan);

    List<PenerbanganModel> getPenerbanganList();

    // void deletePenerbangan(PenerbanganModel penerbangan);

    PenerbanganModel getPenerbanganById (Long id);

    boolean checkPenerbanganByNomorPenerbangan(String nomorPenerbangan);
}
