package apap.tugas.sipes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import apap.tugas.sipes.model.PenerbanganModel;
import apap.tugas.sipes.repository.PenerbanganDb;

import java.time.LocalDate;

@Service
@Transactional
public class PenerbanganServiceImpl implements PenerbanganService{
    @Autowired
    PenerbanganDb penerbanganDb;

    @Override
    public List<PenerbanganModel> getPenerbanganList(){
        return penerbanganDb.findAll();
    }

    @Override
    public void addPenerbangan(PenerbanganModel penerbangan){
        penerbanganDb.save(penerbangan);
    }

    @Override
    public PenerbanganModel getPenerbanganById (Long id){
        // System.out.print(penerbanganDb.existsById(id));
        return penerbanganDb.findById(id).get();
    }

    @Override
    public boolean checkPenerbanganByNomorPenerbangan(String nomorPenerbangan){
        return penerbanganDb.existsByNomorPenerbangan(nomorPenerbangan);
    }

    @Override
    public PenerbanganModel updatePenerbangan(Long id, PenerbanganModel penerbangan){
        PenerbanganModel target = penerbanganDb.findById(id).get();
        target.setKodeBandaraAsal(penerbangan.getKodeBandaraAsal());
        target.setKodeBandaraTujuan(penerbangan.getKodeBandaraTujuan());
        target.setWaktuBerangkat(penerbangan.getWaktuBerangkat());
        target.setNomorPenerbangan(penerbangan.getNomorPenerbangan());
        penerbanganDb.save(target);
        return target;
    }

    @Override
    public void deletePenerbangan(PenerbanganModel penerbangan){
        penerbanganDb.delete(penerbangan);
    }
}
