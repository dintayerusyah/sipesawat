package apap.tugas.sipes.service;

import apap.tugas.sipes.model.PesawatModel;

import java.util.List;

public interface PesawatService {
    void addPesawat(PesawatModel pesawat);

    String generateNomorSeri(PesawatModel pesawat);

    // void updatePesawat(PesawatModel pesawat);

    List<PesawatModel> getPesawatList();

    // void deletePesawat(PesawatModel pesawat);
}
