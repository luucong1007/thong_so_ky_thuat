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
@Table(name = "nhom_hang", schema = "thong_so_ki_thuat")
public class NhomHang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "ma_nhom_hang")
    private String maNhomHang;

    @Column(name = "ten_nhom_hang")
    private String tenNhomHang;

    @Column(name = "xoa")
    private  boolean xoa;
}
