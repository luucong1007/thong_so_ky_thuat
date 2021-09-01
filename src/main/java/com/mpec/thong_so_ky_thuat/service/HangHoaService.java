package com.mpec.thong_so_ky_thuat.service;

import com.mpec.thong_so_ky_thuat.entities.HangHoa;
import com.mpec.thong_so_ky_thuat.entities.dto.HangHoaDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface HangHoaService {
    Page<HangHoa> findAll(Pageable pageable);

    Optional<HangHoa> findById(Integer id);

    Page<HangHoa>search(String ma, String tenHangHoa, Pageable pageable);

    Optional<HangHoa> update(HangHoaDTO hangHoaDTO);

    Boolean delete(int id);

    Optional<HangHoa> save(HangHoaDTO hangHoaDTO);

}
