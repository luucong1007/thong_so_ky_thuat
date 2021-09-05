package com.mpec.thong_so_ky_thuat.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

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
    private String ten;

    @Column(name = "gia_tri")
    private String giaTri;

    @ManyToOne
    @JoinColumn(name = "thong_so_ki_thuat_id") // thông qua khóa ngoại thong_so_ki_thuat_id
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonBackReference
    private ThongSoKiThuat thongSoKiThuat;

    @Column(name = "xoa")
    private boolean xoa;

    @OneToMany(mappedBy = "thongSoChiTiet", cascade = CascadeType.ALL)// Quan hệ 1-n
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // Không sử dụng trong toString()
    private List<HangHoaThongSo> hangHoaThongSos;
}
