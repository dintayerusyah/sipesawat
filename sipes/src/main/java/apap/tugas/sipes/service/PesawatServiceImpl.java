package apap.tugas.sipes.service;

import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import apap.tugas.sipes.model.PesawatModel;
import apap.tugas.sipes.model.TeknisiModel;
import apap.tugas.sipes.repository.PesawatDb;

import java.util.Calendar;
import java.time.LocalDate;
import java.util.Date;
import java.time.ZoneId;
import java.util.ArrayList;

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

    @Override
    public PesawatModel updatePesawat(PesawatModel pesawat){
        pesawatDb.save(pesawat);

        return pesawat;
    }

    @Override
    public List<PesawatModel> getPesawatTua(LocalDate tanggalSekarang){
        Calendar cal = Calendar.getInstance();
        Date today = cal.getTime();
        cal.add(Calendar.YEAR, -10);
        Date previousYear = cal.getTime();
        LocalDate tenYearsAgo = convertToLocalDateViaInstant(previousYear);
        return pesawatDb.findAllWithTanggalDibuatBefore(tenYearsAgo);
    }

    public LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
          .atZone(ZoneId.systemDefault())
          .toLocalDate();
    }

    @Override
    public List<Integer> getAges(List<PesawatModel> daftarPesawat){
        List<Integer> daftarUmur = new ArrayList<Integer>();
        int curYear = LocalDate.now().getYear();
        Integer total = 0;
        for (PesawatModel pesawat : daftarPesawat){
            int year = pesawat.getTanggalDibuat().getYear();
            daftarUmur.add(total, curYear-year);
            total = total+1;
        }
        return daftarUmur;
    }

    @Override
    public List<Integer> getSumTeknisi(List<PesawatModel> daftarPesawat){
        List<Integer> listSumTeknisi = new ArrayList<Integer>();
        Integer total = 0;
        for (PesawatModel pesawat : daftarPesawat){
            Integer totalTeknisi = pesawat.getListTeknisi().size();
            listSumTeknisi.add(total, totalTeknisi);
            total = total+1;
        }
        return listSumTeknisi;
    }

    @Override
    public void deletePesawat(PesawatModel pesawat){
        pesawatDb.delete(pesawat);
    }
}
