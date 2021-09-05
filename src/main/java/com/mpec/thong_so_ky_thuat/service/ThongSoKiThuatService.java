package com.mpec.thong_so_ky_thuat.service;

import com.mpec.thong_so_ky_thuat.entities.ThongSoKiThuat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ThongSoKiThuatService {

    Page<ThongSoKiThuat> findAll(Pageable pageable);

    Optional<ThongSoKiThuat> findById(Integer id);

    Page<ThongSoKiThuat>search(String text, Pageable pageable);

    Optional<ThongSoKiThuat> update(ThongSoKiThuat thongSoKiThuat, int nhomHangId, int nhomThongSoId);

    Boolean delete(int id);

    List<ThongSoKiThuat> findThongSoKiThuatByNhomThongSoId(Integer id);

    Optional<ThongSoKiThuat> save(ThongSoKiThuat thongSoKiThuat, int nhomHangId, int nhomThongSoId);
}
