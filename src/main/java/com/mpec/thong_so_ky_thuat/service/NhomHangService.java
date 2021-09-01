package com.mpec.thong_so_ky_thuat.service;

import com.mpec.thong_so_ky_thuat.entities.NhomHang;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface NhomHangService {
    Page<NhomHang> findAll(Pageable pageable);

    Optional<NhomHang> findById(Integer id);

    Page<NhomHang>search(String maNhomHang, String tenNhomHang, Pageable pageable);

    Optional<NhomHang> update(NhomHang nhomHang);

    Boolean delete(int id);

    Optional<NhomHang> save(NhomHang nhomHang);
}
