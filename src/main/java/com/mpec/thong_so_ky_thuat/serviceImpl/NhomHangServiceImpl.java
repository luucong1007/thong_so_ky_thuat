package com.mpec.thong_so_ky_thuat.serviceImpl;

import com.mpec.thong_so_ky_thuat.entities.NhomHang;
import com.mpec.thong_so_ky_thuat.repository.NhomHangRepo;
import com.mpec.thong_so_ky_thuat.service.NhomHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NhomHangServiceImpl implements NhomHangService {
    @Autowired
    NhomHangRepo nhomHangRepo;

    @Override
    public Page<NhomHang> findAll(Pageable pageable) {
        try {
            return nhomHangRepo.findAll(pageable);
        } catch (Exception e) {
            return Page.empty();
        }
    }

    @Override
    public Optional<NhomHang> findById(Integer id) {
        try {
            return nhomHangRepo.findById(id);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Page<NhomHang> search(String maNhomHang, String tenNhomHang, Pageable pageable) {
        try {
            return nhomHangRepo.search(maNhomHang, tenNhomHang, pageable);
        } catch (Exception e) {
            return Page.empty();
        }
    }

    @Override
    public Optional<NhomHang> update(NhomHang nhomHang) {
        try {
            Optional<NhomHang> monHocOptional = findById(nhomHang.getId());
            if (!monHocOptional.isPresent()) {
                System.out.println("Nhóm hàng id not found");
                return Optional.empty();
            }
            NhomHang nhomHang1 = monHocOptional.get();

            if (nhomHang.getMaNhomHang() != null) {
                nhomHang1.setMaNhomHang(nhomHang.getMaNhomHang());
            }
            if (nhomHang.getTenNhomHang() != null) {
                nhomHang1.setTenNhomHang(nhomHang.getTenNhomHang());
            }
            return Optional.ofNullable(nhomHangRepo.save(nhomHang1));
        } catch (Exception ex) {
            return Optional.empty();
        }
    }

    @Override
    public Boolean delete(int id) {
        try {
            return nhomHangRepo.delete(id) >= 0;
        } catch (Exception ex) {
            System.out.printf(ex.getMessage());
            return false;
        }
    }

    @Override
    public Optional<NhomHang> save(NhomHang nhomHang) {
        try {
            nhomHang.setXoa(false);
            return Optional.ofNullable(nhomHangRepo.save(nhomHang));

        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
