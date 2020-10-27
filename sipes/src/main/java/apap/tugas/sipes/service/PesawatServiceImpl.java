package apap.tugas.sipes.service;

import apap.tugas.sipes.repository.PesawatDb;
import apap.tugas.sipes.model.PesawatModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PesawatServiceImpl implements PesawatService{
    @Autowired
    PesawatDb pesawatDb;

    @Override
    public List<PesawatModel> getPesawatList(){
        return pesawatDb.findAll();
    }
}
