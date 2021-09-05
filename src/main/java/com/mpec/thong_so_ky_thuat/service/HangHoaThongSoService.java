package com.mpec.thong_so_ky_thuat.service;

import com.mpec.thong_so_ky_thuat.entities.HangHoaThongSo;
import com.mpec.thong_so_ky_thuat.entities.ThongSoChiTiet;
import com.mpec.thong_so_ky_thuat.entities.ThongSoKiThuat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface HangHoaThongSoService {
    Page<HangHoaThongSo> findAll(Pageable pageable);

    Optional<HangHoaThongSo> findById(Integer id);

    List<HangHoaThongSo> findByHangHoaId(Integer id);

    List<HangHoaThongSo> findByThongSoKiThuatId(Integer id);

    List<HangHoaThongSo> findByThongSoChiTietId(Integer id);

    Optional<HangHoaThongSo> update(HangHoaThongSo hangHoaThongSo, int hangHoaId, int thongSoKiThuatId, int thongSoChiTietId);

    Boolean delete(int id);

    Optional<HangHoaThongSo> save(HangHoaThongSo hangHoaThongSo, int hagHoaId, int thongSoKiThuatId, int thongSoChiTietId);

}
