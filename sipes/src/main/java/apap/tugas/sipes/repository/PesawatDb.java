package apap.tugas.sipes.repository;

import apap.tugas.sipes.model.PesawatModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.List;
import java.time.LocalDate;

@Repository
public interface PesawatDb extends JpaRepository<PesawatModel, Long>{
    Optional<PesawatModel> findById(Long id);

    @Query("select a from PesawatModel a where a.tanggalDibuat <= :tanggalDibuat")
    List<PesawatModel> findAllWithTanggalDibuatBefore(
      @Param("tanggalDibuat") LocalDate tanggalDibuat);
}
