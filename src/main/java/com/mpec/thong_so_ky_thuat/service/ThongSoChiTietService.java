package com.mpec.thong_so_ky_thuat.service;

import com.mpec.thong_so_ky_thuat.entities.ThongSoChiTiet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ThongSoChiTietService {
    Page<ThongSoChiTiet> findAll(Pageable pageable);

    Optional<ThongSoChiTiet> findById(Integer id);

    Page<ThongSoChiTiet>search(String text, Pageable pageable);

    Optional<ThongSoChiTiet> update(ThongSoChiTiet thongSoChiTiet, int thongSoKiThuatId);

    Boolean delete(int id);

    List<ThongSoChiTiet> findThongSoChiTietByThongSoKiThuatId(Integer id);

    List<ThongSoChiTiet> saveAll(List<ThongSoChiTiet> thongSoChiTiets);

    Optional<ThongSoChiTiet> save(ThongSoChiTiet thongSoChiTiet, int thongSoKiThuatId);
}
