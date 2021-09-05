package com.mpec.thong_so_ky_thuat.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "thong_so_chi_tiet", schema = "thong_so_ki_thuat")
public class ThongSoChiTiet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "ten")
    private  String ten;

    @Column(name = "gia_tri")
    private  String giaTri;

    @ManyToOne
    @JoinColumn(name = "thong_so_ki_thuat_id")
    @JsonBackReference
    private ThongSoKiThuat thongSoKiThuat;

    @Column(name = "xoa")
    private  boolean xoa;

    @Transient
    private List<HangHoaThongSo> hangHoaThongSos;
}
