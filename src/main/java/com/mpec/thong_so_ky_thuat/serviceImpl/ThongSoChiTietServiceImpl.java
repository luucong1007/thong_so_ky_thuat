package com.mpec.thong_so_ky_thuat.serviceImpl;


import com.mpec.thong_so_ky_thuat.entities.ThongSoChiTiet;
import com.mpec.thong_so_ky_thuat.entities.ThongSoKiThuat;
import com.mpec.thong_so_ky_thuat.repository.ThongSoChiTietRepo;
import com.mpec.thong_so_ky_thuat.repository.ThongSoKiThuatRepo;
import com.mpec.thong_so_ky_thuat.service.ThongSoChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ThongSoChiTietServiceImpl implements ThongSoChiTietService {
    @Autowired
    private ThongSoKiThuatRepo thongSoKiThuatRepo;

    @Autowired
    private ThongSoChiTietRepo thongSoChiTietRepo;


    @Override
    public Page<ThongSoChiTiet> findAll(Pageable pageable) {
        try {
            return thongSoChiTietRepo.findAll(pageable);
        }catch (Exception e){
            return Page.empty();
        }
    }

    @Override
    public Optional<ThongSoChiTiet> findById(Integer id) {
        try {
            return thongSoChiTietRepo.findById(id);
        }catch (Exception e){
            return Optional.empty();
        }
    }

    @Override
    public Page<ThongSoChiTiet> search(String text, Pageable pageable) {
        try {
            return thongSoChiTietRepo.search(text, pageable);
        }catch (Exception e){
            return Page.empty();
        }
    }

    @Override
    public Optional<ThongSoChiTiet> update(ThongSoChiTiet thongSoChiTiet, int thongSoKiThuatId) {
        try {
            Optional<ThongSoKiThuat> thongSoKiThuatOptional = thongSoKiThuatRepo.findById(thongSoKiThuatId);
            if (!thongSoKiThuatOptional.isPresent()) {
                System.out.println("Thong so ki thuat Id not found");
                return Optional.empty();
            }
            Optional<ThongSoChiTiet> thongSoChiTietOptional = thongSoChiTietRepo.findById(thongSoChiTiet.getId());
            if (!thongSoChiTietOptional.isPresent()) {
                System.out.println("Thong so chi tiet ID not found");
                return Optional.empty();
            }
            //update
            ThongSoChiTiet thongSoChiTiet1 = thongSoChiTietOptional.get();
            if (thongSoChiTiet.getTen() != null) {
                thongSoChiTiet1.setTen(thongSoChiTiet.getTen());
            }
            if (thongSoChiTiet.getGiaTri() != null) {
                thongSoChiTiet1.setGiaTri(thongSoChiTiet.getGiaTri());
            }
            thongSoChiTiet1.setThongSoKiThuat(thongSoKiThuatOptional.get());
            thongSoChiTiet1.setXoa(false);
            return Optional.ofNullable(thongSoChiTietRepo.save(thongSoChiTiet1));
        } catch (Exception ex) {
            return Optional.empty();
        }
    }

    @Override
    public Boolean delete(int id) {
        try {
            return thongSoChiTietRepo.delete(id) >= 0;
        } catch (Exception ex) {
            System.out.printf(ex.getMessage());
            return false;
        }
    }

    @Override
    public List<ThongSoChiTiet> findThongSoChiTietByThongSoKiThuatId(Integer id) {
        try {
            return thongSoChiTietRepo.findThongSoChiTietByThongSoKiThuatId(id);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<ThongSoChiTiet> saveAll(List<ThongSoChiTiet> thongSoChiTiets) {
        try {
            return thongSoChiTietRepo.saveAll(thongSoChiTiets);
        }
        catch (Exception ex){
            return new ArrayList<>();
        }
    }


    @Override
    public Optional<ThongSoChiTiet> save(ThongSoChiTiet thongSoChiTiet, int thongSoKiThuatId) {
        try {
            Optional<ThongSoKiThuat> thongSoKiThuatOptional = thongSoKiThuatRepo.findById(thongSoKiThuatId);
            if (!thongSoKiThuatOptional.isPresent()) {
                return Optional.empty();
            }

            ThongSoChiTiet thongSoChiTiet1 = new ThongSoChiTiet();
            thongSoChiTiet1.setXoa(false);
            thongSoChiTiet1.setTen(thongSoChiTiet.getTen());
            thongSoChiTiet1.setGiaTri(thongSoChiTiet.getGiaTri());
            thongSoChiTiet1.setThongSoKiThuat(thongSoKiThuatOptional.get());
            return Optional.ofNullable(thongSoChiTietRepo.save(thongSoChiTiet1));
        } catch (Exception ex) {
            return Optional.empty();
        }
    }
}
