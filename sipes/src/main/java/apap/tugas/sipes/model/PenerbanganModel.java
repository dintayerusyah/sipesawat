package apap.tugas.sipes.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;

import java.time.LocalDateTime;

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
    private LocalDateTime waktuBerangkat;

    @NotNull
    @Column(name = "nomor_penerbangan", nullable = false, unique = true)
    private String nomorPenerbangan;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_pesawat", referencedColumnName = "id", nullable = false)
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

    public LocalDateTime getWaktuBerangkat() {
        return this.waktuBerangkat;
    }

    public void setWaktuBerangkat(LocalDateTime waktuBerangkat) {
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
