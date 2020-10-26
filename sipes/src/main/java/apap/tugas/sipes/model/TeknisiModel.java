package apap.tugas.sipes.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.io.Serializable;

import java.util.List;

public class TeknisiModel implements Serializable{
    @Id
    @Size(max = 20)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "nama", nullable = false)
    private String nama;

    @NotNull
    @Column(name = "nomor_telepon", nullable = false)
    private Long nomorTelepon;

    @ManyToMany(mappedBy = "listTeknisi")
    private List<PesawatModel> listPesawat;

    public List<PesawatModel> getListPesawat() {
        return this.listPesawat;
    }

    public void setListPesawat(List<PesawatModel> listPesawat) {
        this.listPesawat = listPesawat;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNama() {
        return this.nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Long getNomorTelepon() {
        return this.nomorTelepon;
    }

    public void setNomorTelepon(Long nomorTelepon) {
        this.nomorTelepon = nomorTelepon;
    }

}
