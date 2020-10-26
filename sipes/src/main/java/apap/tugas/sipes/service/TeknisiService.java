package apap.tugas.sipes.service;

import apap.tugas.sipes.model.TeknisiModel;

import java.util.List;

public interface TeknisiService {
    void addTeknisi(TeknisiModel teknisi);

    void updateTeknisi(TeknisiModel teknisi);

    List<TeknisiModel> getTeknisiList();

    void deleteTeknisi(TeknisiModel teknisi);
}
