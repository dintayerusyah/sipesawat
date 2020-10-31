package apap.tugas.sipes.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

import java.time.LocalDate;

@Entity
@Table(name = "penerbangan")
public class PenerbanganModel implements Serializable{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "kode_bandara_asal", nullable = false)
    private String kodeBandaraAsal;

    @NotNull
    @Column(name = "kode_bandara_tujuan", nullable = false)
    private String kodeBandaraTujuan;

    @NotNull
    @Column(name = "waktu_berangkat", nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate waktuBerangkat;

    @NotNull
    @Column(name = "nomor_penerbangan", nullable = false, unique = true)
    private String nomorPenerbangan;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "id_pesawat", referencedColumnName = "id", nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PesawatModel pesawat;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKodeBandaraAsal() {
        return this.kodeBandaraAsal;
    }

    public void setKodeBandaraAsal(String kodeBandaraAsal) {
        this.kodeBandaraAsal = kodeBandaraAsal;
    }

    public String getKodeBandaraTujuan() {
        return this.kodeBandaraTujuan;
    }

    public void setKodeBandaraTujuan(String kodeBandaraTujuan) {
        this.kodeBandaraTujuan = kodeBandaraTujuan;
    }

    public LocalDate getWaktuBerangkat() {
        return this.waktuBerangkat;
    }

    public void setWaktuBerangkat(LocalDate waktuBerangkat) {
        this.waktuBerangkat = waktuBerangkat;
    }

    public String getNomorPenerbangan() {
        return this.nomorPenerbangan;
    }

    public void setNomorPenerbangan(String nomorPenerbangan) {
        this.nomorPenerbangan = nomorPenerbangan;
    }

    public PesawatModel getPesawat() {
        return this.pesawat;
    }

    public void setPesawat(PesawatModel pesawat) {
        this.pesawat = pesawat;
    }

}
