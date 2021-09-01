package com.mpec.thong_so_ky_thuat.service;

import com.mpec.thong_so_ky_thuat.entities.ThuongHieu;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.util.Optional;

@Service
public interface ThuongHieuService {
    Page<ThuongHieu> findAll(Pageable pageable);

    Optional<ThuongHieu> findById(Integer id);

    Page<ThuongHieu>search(String text, Pageable pageable);

    Boolean updateTenThuongHieu(int id, String tenThuongHieu);

    Boolean delete(int id);

    Optional<ThuongHieu> save(ThuongHieu thuongHieu);
}
