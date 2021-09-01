package com.mpec.thong_so_ky_thuat.service;

import com.mpec.thong_so_ky_thuat.entities.NhomThongSo;
import com.mpec.thong_so_ky_thuat.entities.dto.HangHoaDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface NhomThongSoService {
    Page<NhomThongSo> findAll(Pageable pageable);

    Optional<NhomThongSo> findById(Integer id);

    Page<NhomThongSo>search(String ma, String tenNhomThongSo, Pageable pageable);

    Optional<NhomThongSo> update(NhomThongSo nhomThongSo, int hangHoaid);

    Boolean delete(int id);

    Optional<NhomThongSo> save(NhomThongSo nhomThongSo, int hangHoaId);
}
