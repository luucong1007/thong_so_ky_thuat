package com.mpec.thong_so_ky_thuat.entities;



import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

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
    @JsonBackReference(value = "hang-hoa")
    private HangHoa hangHoa;

    @ManyToOne
    @JoinColumn(name = "thong_so_ki_thuat_id")
    @JsonBackReference(value = "thong-so-ki-thuat")
    private ThongSoKiThuat thongSoKiThuat;

    @ManyToOne
    @JoinColumn(name = "thong_so_chi_tiet_id")
    @JsonBackReference(value = "thong-so-chi-tiet")
    private ThongSoChiTiet thongSoChiTiet;


    @Column(name = "gia_tri")
    private  String giaTri;

    @Column(name = "xoa")
    private  boolean xoa;
}
