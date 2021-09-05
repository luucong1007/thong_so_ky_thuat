package com.mpec.thong_so_ky_thuat.entities;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
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
    private String tenThongSo;

    @ManyToOne
    @JoinColumn(name = "nhom_hang_id")
    private NhomHang nhomHang;

    @ManyToOne
    @JoinColumn(name = "nhom_thong_so_id")
    @JsonBackReference
    private NhomThongSo nhomThongSo;

    @Column(name = "xoa")
    private boolean xoa;

    @OneToMany(mappedBy = "thongSoKiThuat", cascade = CascadeType.ALL)// Quan hệ 1-n
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // Không sử dụng trong toString()
    private List<ThongSoChiTiet> thongSoChiTiets;

    @OneToMany(mappedBy = "thongSoKiThuat", cascade = CascadeType.ALL)// Quan hệ 1-n
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // Không sử dụng trong toString()
    private List<HangHoaThongSo> hangHoaThongSos;

}
