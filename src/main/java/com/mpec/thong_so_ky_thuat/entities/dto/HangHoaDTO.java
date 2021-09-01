package com.mpec.thong_so_ky_thuat.entities.dto;

import com.mpec.thong_so_ky_thuat.entities.NhomHang;
import com.mpec.thong_so_ky_thuat.entities.ThuongHieu;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
public class HangHoaDTO {
    private int id;

    private String ma;

    private String maGiamGia;

    private String moTa;

    private Float phanTramgiamGia;

    private String tenHangHoa;

    private Integer tichDiem;

    private Integer nhomHangId;

    private Integer thuongHieuId;

    private  String url1;

    private  String url2;

    private  String url3;

    private  boolean xoa;

    // validate dữ liệu đầu vào
}
