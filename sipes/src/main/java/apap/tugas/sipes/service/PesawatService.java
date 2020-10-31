package apap.tugas.sipes.service;

import apap.tugas.sipes.model.PesawatModel;

import java.util.List;
import java.time.LocalDate;

public interface PesawatService {
    void addPesawat(PesawatModel pesawat);

    String generateNomorSeri(PesawatModel pesawat);

    PesawatModel updatePesawat(PesawatModel pesawat);

    List<PesawatModel> getPesawatList();

    // void deletePesawat(PesawatModel pesawat);

    PesawatModel getPesawatById(Long id);

    List<PesawatModel> getPesawatTua(LocalDate tanggalSekarang);

    List<Integer> getAges(List<PesawatModel> daftarPesawat)
}
