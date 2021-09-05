package com.mpec.thong_so_ky_thuat.serviceImpl;

import com.mpec.thong_so_ky_thuat.entities.*;
import com.mpec.thong_so_ky_thuat.repository.*;
import com.mpec.thong_so_ky_thuat.service.HangHoaThongSoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HangHoaThongSoImpl implements HangHoaThongSoService {

    @Autowired
    private HangHoaThongSoRepo hangHoaThongSoRepo;

    @Autowired
    private HangHoaRepo hangHoaRepo;

    @Autowired
    private ThongSoKiThuatRepo thongSoKiThuatRepo;

    @Autowired
    private ThongSoChiTietRepo thongSoChiTietRepo;

    @Override
    public Page<HangHoaThongSo> findAll(Pageable pageable) {
        try {
            return hangHoaThongSoRepo.findAll(pageable);
        } catch (Exception e) {
            return Page.empty();
        }
    }

    @Override
    public Optional<HangHoaThongSo> findById(Integer id) {
        try {
            return hangHoaThongSoRepo.findById(id);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public List<HangHoaThongSo> findByHangHoaId(Integer id) {
        try {
            return hangHoaThongSoRepo.findByHangHoaId(id);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @Override
    public List<HangHoaThongSo> findByThongSoKiThuatId(Integer id) {
        try {
            return hangHoaThongSoRepo.findByThongSoKiThuatId(id);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @Override
    public List<HangHoaThongSo> findByThongSoChiTietId(Integer id) {
        try {
            return hangHoaThongSoRepo.findByThongSoChiTiet(id);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @Override
    public Optional<HangHoaThongSo> update(HangHoaThongSo hangHoaThongSo, int hangHoaId, int thongSoKiThuatId, int thongSoChiTietId) {
        try {
            Optional<HangHoa> hangHoaOptional = hangHoaRepo.findById(hangHoaId);
            if (!hangHoaOptional.isPresent()) {
                System.out.println("hangHoaID not found");
                return Optional.empty();
            }

            Optional<ThongSoKiThuat> thongSoKiThuatOptional = thongSoKiThuatRepo.findById(thongSoKiThuatId);
            if (!thongSoKiThuatOptional.isPresent()) {
                System.out.println("tskt id not found");
                return Optional.empty();
            }

            Optional<ThongSoChiTiet> thongSoChiTietOptional = thongSoChiTietRepo.findById(thongSoChiTietId);
            if (!thongSoChiTietOptional.isPresent()) {
                System.out.println("tsct id not found");
                return Optional.empty();
            }

            Optional<HangHoaThongSo> hangHoaThongSoOptional = hangHoaThongSoRepo.findById(hangHoaThongSo.getId());
            if (!hangHoaThongSoOptional.isPresent()) {
                System.out.println("hang hoa thong so id id not found");
                return Optional.empty();
            }


            //update
            HangHoaThongSo hangHoaThongSo1 = hangHoaThongSoOptional.get();
            if (hangHoaThongSo.getGiaTri() != null) {
                hangHoaThongSo1.setGiaTri(hangHoaThongSo.getGiaTri());
            }
            hangHoaThongSo1.setHangHoa(hangHoaOptional.get());
            hangHoaThongSo1.setThongSoKiThuat(thongSoKiThuatOptional.get());
            hangHoaThongSo1.setThongSoChiTiet(thongSoChiTietOptional.get());
            hangHoaThongSo1.setXoa(false);

            return Optional.ofNullable(hangHoaThongSoRepo.save(hangHoaThongSo1));
        } catch (Exception ex) {
            return Optional.empty();
        }
    }


    @Override
    public Boolean delete(int id) {
        try {
            return hangHoaThongSoRepo.delete(id) >= 0;
        } catch (Exception ex) {
            System.out.printf(ex.getMessage());
            return false;
        }
    }

    @Override
    public Optional<HangHoaThongSo> save(HangHoaThongSo hangHoaThongSo, int hangHoaId, int thongSoKiThuatId, int thongSoChiTietId) {
        try {

            Optional<HangHoa> hangHoaOptional = hangHoaRepo.findById(hangHoaId);
            if (!hangHoaOptional.isPresent()) {
                return Optional.empty();
            }

            Optional<ThongSoKiThuat> thongSoKiThuatOptional = thongSoKiThuatRepo.findById(thongSoKiThuatId);
            if (!thongSoKiThuatOptional.isPresent()) {
                return Optional.empty();
            }

            Optional<ThongSoChiTiet> thongSoChiTietOptional = thongSoChiTietRepo.findById(thongSoChiTietId);
            if (!thongSoChiTietOptional.isPresent()) {
                return Optional.empty();
            }
            // save
            HangHoaThongSo hangHoaThongSo1 = new HangHoaThongSo();
            hangHoaThongSo1.setXoa(false);
            hangHoaThongSo1.setGiaTri(hangHoaThongSo.getGiaTri());
            hangHoaThongSo1.setHangHoa(hangHoaOptional.get());
            hangHoaThongSo1.setThongSoChiTiet(thongSoChiTietOptional.get());
            hangHoaThongSo1.setThongSoKiThuat(thongSoKiThuatOptional.get());
            return Optional.ofNullable(hangHoaThongSoRepo.save(hangHoaThongSo1));
        } catch (Exception ex) {
            return Optional.empty();
        }
    }
}
