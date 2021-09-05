package com.mpec.thong_so_ky_thuat.serviceImpl;


import com.mpec.thong_so_ky_thuat.entities.NhomHang;
import com.mpec.thong_so_ky_thuat.entities.NhomThongSo;
import com.mpec.thong_so_ky_thuat.entities.ThongSoChiTiet;
import com.mpec.thong_so_ky_thuat.entities.ThongSoKiThuat;
import com.mpec.thong_so_ky_thuat.repository.*;
import com.mpec.thong_so_ky_thuat.service.ThongSoKiThuatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ThongSoKiThuatServiceImpl implements ThongSoKiThuatService {

    @Autowired
    private ThongSoKiThuatRepo thongSoKiThuatRepo;

    @Autowired
    private ThongSoChiTietRepo thongSoChiTietRepo;

    @Autowired
    private NhomThongSoRepo nhomThongSoRepo;

    @Autowired
    private NhomHangRepo nhomHangRepo;


    @Override
    public Page<ThongSoKiThuat> findAll(Pageable pageable) {
        try {
            return thongSoKiThuatRepo.findAll(pageable);
        }catch (Exception e){
            return Page.empty();
        }
    }

    @Override
    public Optional<ThongSoKiThuat> findById(Integer id) {
        try {
            return thongSoKiThuatRepo.findById(id);
        }catch (Exception e){
            return Optional.empty();
        }
    }

    @Override
    public Page<ThongSoKiThuat> search(String text, Pageable pageable) {
        try {
            return thongSoKiThuatRepo.search(text, pageable);
        }catch (Exception e){
            return Page.empty();
        }
    }

    @Override
    public Optional<ThongSoKiThuat> update(ThongSoKiThuat thongSoKiThuat, int nhomHangId, int nhomThongSoId) {
        try {
            Optional<NhomHang> nhomHangOptional = nhomHangRepo.findById(nhomHangId);
            if (!nhomHangOptional.isPresent()) {
                System.out.println("NhomHangId not found");
                return Optional.empty();
            }
            Optional<NhomThongSo> nhomThongSoOptional = nhomThongSoRepo.findById(nhomThongSoId);
            if (!nhomThongSoOptional.isPresent()) {
                System.out.println("NHomThongSoID not found");
                return Optional.empty();
            }
            Optional<ThongSoKiThuat> thongSoKiThuatOptional = thongSoKiThuatRepo.findById(thongSoKiThuat.getId());
            if (!nhomThongSoOptional.isPresent()) {
                System.out.println("ThongSoKiThuatId not found");
                return Optional.empty();
            }
            //update
            ThongSoKiThuat thongSoKiThuat1 = thongSoKiThuatOptional.get();
            if (thongSoKiThuat.getTenThongSo() != null) {
                thongSoKiThuat1.setTenThongSo(thongSoKiThuat.getTenThongSo());
            }
            thongSoKiThuat1.setNhomHang(nhomHangOptional.get());
            thongSoKiThuat1.setNhomThongSo(nhomThongSoOptional.get());
            thongSoKiThuat1.setXoa(false);
            return Optional.ofNullable(thongSoKiThuatRepo.save(thongSoKiThuat1));
        } catch (Exception ex) {
            return Optional.empty();
        }
    }

    @Override
    public Boolean delete(int id) {
        try {
            return thongSoKiThuatRepo.delete(id) >= 0;
        } catch (Exception ex) {
            System.out.printf(ex.getMessage());
            return false;
        }
    }

    @Override
    public List<ThongSoKiThuat> findThongSoKiThuatByNhomThongSoId(Integer id) {
        try {
            return thongSoKiThuatRepo.findThongSoKiThuatByNhomThongSoId(id);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public Optional<ThongSoKiThuat> save(ThongSoKiThuat thongSoKiThuat, int nhomHangId, int nhomThongSoId) {
        try {
            Optional<NhomHang> nhomHangOptional = nhomHangRepo.findById(nhomHangId);
            if (!nhomHangOptional.isPresent()) {
                return Optional.empty();
            }

            Optional<NhomThongSo> nhomThongSoOptional = nhomThongSoRepo.findById(nhomThongSoId);
            if (!nhomHangOptional.isPresent()) {
                return Optional.empty();
            }
            ThongSoKiThuat thongSoKiThuat1 = new ThongSoKiThuat();
            thongSoKiThuat1.setXoa(false);
            thongSoKiThuat1.setTenThongSo(thongSoKiThuat.getTenThongSo());
            thongSoKiThuat1.setNhomThongSo(nhomThongSoOptional.get());
            thongSoKiThuat1.setNhomHang(nhomHangOptional.get());
            return Optional.ofNullable(thongSoKiThuatRepo.save(thongSoKiThuat1));

//            List<ThongSoChiTiet> thongSoChiTiets = new ArrayList<>();
//                ThongSoChiTiet tsct = new ThongSoChiTiet();
//                tsct.setGiaTri(thongSoChiTiets.get());
//                thongSoChiTiets.add(tsct);
//
//            List<ThongSoChiTiet> thongSoChiTiet1 = thongSoChiTietRepo.saveAll(thongSoChiTiets);
        } catch (Exception ex) {
            return Optional.empty();
        }
    }
}
