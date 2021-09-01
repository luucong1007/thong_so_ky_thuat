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
@Table(name = "thuong_hieu", schema = "thong_so_ki_thuat")
public class ThuongHieu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "ten_thuong_hieu")
    private String tenThuongHieu;

    @Column(name = "xoa")
    private  short xoa;
}
