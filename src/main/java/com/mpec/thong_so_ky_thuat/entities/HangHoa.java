package com.mpec.thong_so_ky_thuat.entities;

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
@Table(name = "hang_hoa", schema = "thong_so_ki_thuat")
public class HangHoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "ma")
    private String ma;

    @Column(name = "ma_giam_gia")
    private String maGiamGia;

    @Column(name = "mo_ta")
    private String moTa;

    @Column(name = "phan_tram_giam_gia")
    private Float phanTramgiamGia;

    @Column(name = "tenHangHoa")
    private String tenHangHoa;

    @Column(name = "tich_diem")
    private Integer tichDiem;

    @ManyToOne
    @JoinColumn(name = "nhom_hang_id")
    private NhomHang nhomHang;

    @ManyToOne
    @JoinColumn(name = "thuong_hieu_id")
    private ThuongHieu thuongHieu;

    @Column(name = "url_hinh_anh_1")
    private  String url1;

    @Column(name = "url_hinh_anh_2")
    private  String url2;

    @Column(name = "url_hinh_anh_3")
    private  String url3;

    @Column(name = "xoa")
    private  boolean xoa;
}
