package com.mpec.thong_so_ky_thuat.serviceImpl;

import com.mpec.thong_so_ky_thuat.entities.ThuongHieu;
import com.mpec.thong_so_ky_thuat.repository.ThuongHieuRepo;
import com.mpec.thong_so_ky_thuat.service.ThuongHieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class ThuongHieuServiceImpl implements ThuongHieuService {

    @Autowired
    ThuongHieuRepo thuongHieuRepo;


    @Override
    public Page<ThuongHieu> findAll(Pageable pageable) {
        try {
            return thuongHieuRepo.findAll(pageable);
        } catch (Exception e) {
            return Page.empty();
        }
    }

    @Override
    public Optional<ThuongHieu> findById(Integer id) {
        try {
            return thuongHieuRepo.findById(id);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Page<ThuongHieu> search(String text, Pageable pageable) {
        try {
            return thuongHieuRepo.search(text, pageable);
        } catch (Exception e) {
            return Page.empty();
        }
    }

    @Override
    public Boolean updateTenThuongHieu(int id, String tenThuongHieu) {
        try {
           return thuongHieuRepo.updateTenThuongHieu(id, tenThuongHieu) >=0;
        } catch (Exception ex) {
            System.out.printf(ex.getMessage());
            return false;
        }
    }



    @Override
    public Boolean delete(int id) {
        try {
            return thuongHieuRepo.delete(id) >= 0;
        } catch (Exception ex) {
            System.out.printf(ex.getMessage());
            return false;
        }
    }

    @Override
    public Optional<ThuongHieu> save(ThuongHieu thuongHieu) {
        try {
            return Optional.ofNullable(thuongHieuRepo.save(thuongHieu));
        } catch (Exception ex) {
            System.out.println("EX : " + ex.getMessage());
            return Optional.empty();
        }
    }
}
