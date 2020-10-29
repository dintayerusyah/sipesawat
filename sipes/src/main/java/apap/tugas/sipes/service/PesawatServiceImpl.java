package apap.tugas.sipes.service;

import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import apap.tugas.sipes.model.PesawatModel;
import apap.tugas.sipes.repository.PesawatDb;

@Service
@Transactional
public class PesawatServiceImpl implements PesawatService{
    @Autowired
    PesawatDb pesawatDb;

    @Override
    public List<PesawatModel> getPesawatList(){
        return pesawatDb.findAll();
    }

    @Override
    public void addPesawat(PesawatModel pesawat){
        pesawatDb.save(pesawat);
    }

    @Override
    public String generateNomorSeri(PesawatModel pesawat){
        String nomorSeri = "";
        // 1 karakter jenis pesawat
        String jenisPesawat = pesawat.getJenisPesawat();

        // 2 karakter tipe pesawat
        String tipePesawat = "";
        if (pesawat.getTipe().getId() == 1){
            tipePesawat = "BO";
        }
        else if(pesawat.getTipe().getId() == 2){
            tipePesawat = "AT";
        }
        else if(pesawat.getTipe().getId() == 3){
            tipePesawat = "AB";
        }
        else{
            tipePesawat = "BB";
        }

        // 4 karakter tahun pesawat dibalik
        String tahunPesawat = Integer.toString(pesawat.getTanggalDibuat().getYear());
        StringBuilder output = new StringBuilder();
        output.append(tahunPesawat).reverse();
        String tahunPesawatA = output.toString();


        // 4 karakter tahun pesawat + 8
        String tahunPesawatB = Integer.toString((pesawat.getTanggalDibuat().getYear() + 8));
 

        // 2 karakter huruf kapital random
        String random = RandomStringUtils.randomAlphabetic(2).toUpperCase();

        nomorSeri = jenisPesawat + tipePesawat + tahunPesawatA + tahunPesawatB + random;
        return nomorSeri;
    }

    @Override
    public PesawatModel getPesawatById(Long id){
        return pesawatDb.findById(id).get();
    }
}
