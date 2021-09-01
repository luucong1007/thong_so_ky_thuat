package com.mpec.thong_so_ky_thuat.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "nhom_thong_so", schema = "thong_so_ki_thuat")
public class NhomThongSo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "ten_nhom_thong_so")
    private  String tenNhomThongSo;

    @ManyToOne
    @JoinColumn(name = "hang_hoa_id")
    private HangHoa hangHoa;

    @Column(name = "ma_nhom_thong_so")
    private  String maNhomThongSo;

    @Column(name = "xoa")
    private  boolean xoa;
}
