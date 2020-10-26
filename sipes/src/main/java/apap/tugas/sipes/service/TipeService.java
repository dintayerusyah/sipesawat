package apap.tugas.sipes.service;

import apap.tugas.sipes.model.TipeModel;

import java.util.List;

public interface TipeService {
    void addTipe(TipeModel tipe);

    void updateTipe(TipeModel tipe);

    List<TipeModel> getTipeList();

    void deleteTipe(TipeModel tipe);
}
