package apap.tugas.sipes.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "pesawat")
public class PesawatModel implements Serializable{
    @Id
    @Size(max = 20)
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "maskapai", nullable = false)
    private String maskapai;

    @NotNull
    @Column(name = "nomor_seri", nullable = false, unique = true)
    private String nomorSeri;

    @NotNull
    @Column(name = "tempat_dibuat", nullable = false)
    private String tempatDibuat;

    @NotNull
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    @Column(name = "tanggal_dibuat", nullable = false)
    private LocalDate tanggalDibuat;

    @NotNull
    @Column(name = "jenis_pesawat", nullable = false)
    private String jenisPesawat;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_tipe", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private TipeModel tipe;

    @OneToMany(mappedBy = "pesawat", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PenerbanganModel> listPenerbangan;

    @ManyToMany
    @JoinTable(
        name = "pesawat_teknisi",
        joinColumns = @JoinColumn(name = "id_pesawat"),
        inverseJoinColumns = @JoinColumn(name = "id_teknisi")
    )
    private List<TeknisiModel> listTeknisi;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaskapai() {
        return this.maskapai;
    }

    public void setMaskapai(String maskapai) {
        this.maskapai = maskapai;
    }

    public String getNomorSeri() {
        return this.nomorSeri;
    }

    public void setNomorSeri(String nomorSeri) {
        this.nomorSeri = nomorSeri;
    }

    public String getTempatDibuat() {
        return this.tempatDibuat;
    }

    public void setTempatDibuat(String tempatDibuat) {
        this.tempatDibuat = tempatDibuat;
    }

    public LocalDate getTanggalDibuat() {
        return this.tanggalDibuat;
    }

    public void setTanggalDibuat(LocalDate tanggalDibuat) {
        this.tanggalDibuat = tanggalDibuat;
    }

    public String getJenisPesawat() {
        return this.jenisPesawat;
    }

    public void setJenisPesawat(String jenisPesawat) {
        this.jenisPesawat = jenisPesawat;
    }

    public TipeModel getTipe() {
        return this.tipe;
    }

    public void setTipe(TipeModel tipe) {
        this.tipe = tipe;
    }

    public List<PenerbanganModel> getListPenerbangan() {
        return this.listPenerbangan;
    }

    public void setListPenerbangan(List<PenerbanganModel> listPenerbangan) {
        this.listPenerbangan = listPenerbangan;
    }

    public List<TeknisiModel> getListTeknisi() {
        return this.listTeknisi;
    }

    public void setListTeknisi(List<TeknisiModel> listTeknisi) {
        this.listTeknisi = listTeknisi;
    }

}