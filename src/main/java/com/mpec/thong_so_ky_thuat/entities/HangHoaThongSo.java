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
@Table(name = "hang_hoa_thong_so", schema = "thong_so_ki_thuat")
public class HangHoaThongSo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "hang_hoa_id")
    private HangHoa hangHoa;

    @ManyToOne
    @JoinColumn(name = "thong_so_ki_thuat_id")
    private ThongSoKiThuat thongSoKiThuat;

    @ManyToOne
    @JoinColumn(name = "thong_so_chi_tiet_id")
    private ThongSoChiTiet thongSoChiTiet;

    @Column(name = "gia_tri")
    private  String giaTri;

    @Column(name = "xoa")
    private  boolean xoa;
}
