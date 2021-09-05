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
@Table(name = "thong_so_ki_thuat", schema = "thong_so_ki_thuat")
public class ThongSoKiThuat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "ten_thong_so")
    private  String tenThongSo;

    @ManyToOne
    @JoinColumn(name = "nhom_hang_id")
    private NhomHang nhomHang;

    @ManyToOne
    @JoinColumn(name = "nhom_thong_so_id")
    @JsonBackReference
    private NhomThongSo nhomThongSo;

    @Column(name = "xoa")
    private  boolean xoa;

    @Transient
    private List<ThongSoChiTiet> thongSoChiTiets;

    @Transient
    private List<HangHoaThongSo> hangHoaThongSos;

}
